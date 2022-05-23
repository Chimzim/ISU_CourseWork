#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <pthread.h>
#include <sys/time.h>
#include "bank.h"
#include "bank.c"
#include "queue.c"

/*-------------Prototypes---------------------------------*/
int initServer(int num, char * arguements[]);
void userRequest();
void initAccountMutex(pthread_mutex_t mutex);
void createWorkers();
void *serviceRequest(int id);
/*-------------Static Data-------------------------------*/
int numWorkers, numAccounts, accepted = -1, idRequest = 1; /*(inorder) Number of workers, number of accounts, accepting request, ID of the request */
char usersRequest[100] = "null"; //users input
char * filename; //variable for the file to create 
pthread_mutex_t accountMutex; //account mutex
pthread_cond_t request; //conditional variable
struct queue Queue; //queue variable
FILE *file;

int main(int argc, char * argv[]) {
  int myTest, i;
  myTest = initServer(argc, argv);

  file = fopen(filename, "w");

 initQueue(numAccounts);
  accepted = accepting();

  pthread_mutex_init(&accountMutex, NULL);
    pthread_cond_init(&request, NULL);

  int takingReq = 0;
  pthread_t myWorkers[numWorkers];
  int thread_index[numWorkers];
  for(i = 0; i < numWorkers; i++) {
      thread_index[i] = i;
      pthread_create(&myWorkers[i], NULL, serviceRequest, NULL);
  }
  /*This while loop will run until there are no more items in the queue and the queue/bank/server is not servicing anymore request. It will ask for the users request
then lock the mutex update the queue and if it can accept then add to the queue and if the number of jobs is greater than 0 it broadcast to the worker threads and then unlocks*/
  while(takingReq == 0) {
    userRequest();
    pthread_mutex_lock(&accountMutex);

    accepted = accepting();
    if(accepted == 0) {
      Queue = addRequest(usersRequest, idRequest);
      accepted = accepting();
    }

    if(accepted == 1 && Queue.num_jobs == 0) {
      pthread_mutex_unlock(&accountMutex);
      break;
    }
    
     if(Queue.num_jobs > 0) {
      pthread_cond_broadcast(&request);
    }
    pthread_mutex_unlock(&accountMutex);
  }
  /*  for(i = 0; i < numWorkers; i++) {
    pthread_join(myWorkers[i], NULL);
    }*/
  fclose(file);

return 0;
}

/*Inits the bank accounts: Sets the number of accounts and workers and grabs the filename*/
int initServer(int num, char * arguements[]) {
   int test = -1; /**/
   if(num >= 4) {/**/
     numWorkers = atoi(arguements[1]);//
     numAccounts = atoi(arguements[2]);
     test = initialize_accounts(numAccounts);//
     filename = arguements[3];
   }
   //  printf("Success = %d\n", test);
   return test;//
}

void userRequest() {
  printf("> ");//
  fgets(usersRequest, 100, stdin);//
}

/*Serivce request for the accounts*/
void *serviceRequest(int id) {
  accepted = accepting();
  int done = 0;
  while(done == 0) {
    int ID = ((int *) id);
    Queue = getQueue();//
    accepted = accepting();

    if(accepted == 1 && Queue.num_jobs == 0) {
      printf("Thread exit taken\n");
      break;
    }

    pthread_mutex_lock(&accountMutex);
    while(Queue.num_jobs <= 0 && accepted == 0) {
      pthread_cond_wait(&request, &accountMutex);
      Queue = getQueue();
    } 
  /*
    --TODO write to output file--
   */
    struct request * toService;//
    toService = removeRequest(Queue);
    if(toService->check_acc_id == -1) {
      int i, process = 1;
      for(i = 0; i < toService->num_transfers; i++) {
	int accountBal = read_account(toService->transfers[i].acc_id);
	int temp = accountBal + toService->transfers[i].amount;
	if(temp < 0) {
	  struct timeval time;
	  gettimeofday(&time, NULL);
	  process = -1;
	  fprintf(file,"ISF %d TIME %ld %06ld\n", i, toService->starttime.tv_sec, time);
	  break;
	}
	if(process != -1) {
	  for(i = 0; i < toService->num_transfers; i++) {
	    int accountBal = read_account(toService->transfers[i].acc_id);
	    int temp = accountBal + toService->transfers[i].amount;
	    write_account(toService->transfers[i].acc_id, temp);
	  }
	  struct timeval time;
	  gettimeofday(&time, NULL);
	  fprintf(file,"OK TIME %ld %06ld\n", toService->starttime.tv_sec, time);
	}
      }
    }else {
      int accountBal = read_account(toService->check_acc_id);
      gettimeofday(&toService->endtime, NULL);
      fprintf(file,"BAL %d TIME %ld %06ld\n", accountBal, toService->starttime.tv_sec, toService->endtime.tv_sec);
    }
    idRequest += 1;
    pthread_mutex_unlock(&accountMutex); //
  } 
}
