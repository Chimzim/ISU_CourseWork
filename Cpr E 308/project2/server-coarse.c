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
int numWorkers, currentWorkers = 0, numAccounts, accepted = -1, idRequest = 1;
char usersRequest[100] = "null";
char * filename;
pthread_mutex_t * accountMutex;
pthread_cond_t request;
struct queue Queue;
FILE *file;

int main(int argc, char * argv[]) {
  int myTest, i;
  myTest = initServer(argc, argv);

  file = fopen(filename, "w");

 initQueue(numAccounts);
  accepted = accepting();

  accountMutex = malloc(numAccounts * sizeof(pthread_mutex_t*));
  for(i = 0; i < numAccounts; i++) {
    pthread_mutex_init(&accountMutex[i], NULL);
  }
    pthread_cond_init(&request, NULL);

  int takingReq = 0;
  pthread_t myWorkers[numWorkers];
  int thread_index[numWorkers];
  for(i = 0; i < numWorkers; i++) {
      thread_index[i] = i;
      pthread_create(&myWorkers[i], NULL, serviceRequest, NULL);
  }

  while(takingReq == 0) {
    userRequest();
    int m = 0;
    for(m = 0; m < numAccounts; m++) {
      pthread_mutex_lock(&accountMutex[m]);//
    }

    accepted = accepting();
    if(accepted == 0) {
      Queue = addRequest(usersRequest, idRequest);
      accepted = accepting();
    }

    if(accepted == 1 && Queue.num_jobs == 0) {
      for(m = 0; m < numAccounts; m++) {
       pthread_mutex_unlock(&accountMutex[m]);//
     }
      break;
    }
    
     if(Queue.num_jobs > 0) {
      pthread_cond_broadcast(&request);
    }
     for(m = 0; m < numAccounts; m++) {
       pthread_mutex_unlock(&accountMutex[m]);//
     }
  }
  /*  for(i = 0; i < numWorkers; i++) {
    pthread_join(myWorkers[i], NULL);
    } */
  fclose(file);

return 0;
}


int initServer(int num, char * arguements[]) {
   int test = -1; /**/
   if(num >= 4) {/**/
     numWorkers = atoi(arguements[1]);//
     numAccounts = atoi(arguements[2]);
     test = initialize_accounts(numAccounts);//
     filename = arguements[3];
   }
   printf("Success = %d\n", test);
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
