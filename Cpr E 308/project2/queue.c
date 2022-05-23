#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/time.h>
#include <stdlib.h>

/*Queue Implementation*/
struct queue myQueue;
int maxAccount;
int canAdd = 0;
int num_trans = 0;
int toAdd[10][2];

/* struct timeval { I think professor Mat said I should be using this from the library
  time_t tv_sec;
  suseconds_t tv_usec;
}; */

struct transfer { // structure for a transfer pair within a transaction
	int acc_id; // account ID
	int amount; // amount to be added, could be positive or negative
};

struct request {
	struct request * next; // pointer to the next request in the list
	int request_id; // request ID assigned by the main thread
	int check_acc_id; // account ID for a CHECK request
	struct transfer * transfers; // array of transfers if a TRANS request
	int num_transfers; // number of transfers if a TRANS request
	struct timeval starttime, endtime; // starttime and endtime for TIME
};

struct queue {
	struct request * head, * tail; // head and tail of the list
	int num_jobs; // number of jobs currently in queue
};

/*This function adds items to the queue by Calling isTransValid and isCheckValid which determine if the request is valid. It then checks to see if the 
queue is empty (num_jobs) if the queue is empty to sets the request to the head object and then makes the next point at the tail. If it isn't empty
it creates at the tail object and then mallocs the tail.next and then increments the tail to now be tail.next: Note items are only added when canAdd is zero, when END is recieved canAdd changes to 1*/
struct queue addRequest(char request[], int ID) {
  char * myToken = strtok(request, " \n");
  /*-----------------------------------------------------------------*/
  if(strcmp(myToken, "TRANS") == 0) {
    int valid = isTransValid(myToken);
   if(valid == 1 && canAdd == 0) {
    if(myQueue.num_jobs == 0) {
      //myQueue.head->starttime = malloc(sizeof(struct timeval));
      gettimeofday(&myQueue.head->starttime, NULL);
      myQueue.head->request_id = ID;
      myQueue.head->check_acc_id = -1;   /*Denotes that this request isn't a CHECK*/

      int i = 0;
      myQueue.head->transfers = malloc(10 * sizeof(struct transfer));
      for(i = 0; i < num_trans; i++) {
	myQueue.head->transfers[i].acc_id = toAdd[i][1];
	myQueue.head->transfers[i].amount = toAdd[i][0];
      }
      myQueue.head->num_transfers = i;
      myQueue.head->next = myQueue.tail;
      myQueue.tail->next = NULL;
      myQueue.num_jobs += 1;
      myQueue.tail->next = malloc(sizeof(struct queue));
      //   myQueue.tail->next->starttime = malloc(sizeof(struct timeval));
    }else {
      //    myQueue.tail->starttime = malloc(sizeof(struct timeval));
      gettimeofday(&myQueue.tail->starttime, NULL);
      myQueue.tail->request_id = ID;
      myQueue.tail->check_acc_id = -1;
      int j = 0;

      myQueue.tail->transfers = malloc(10 * sizeof(struct transfer));
      for(j = 0; j < num_trans; j++) {
	myQueue.tail->transfers[j].acc_id = toAdd[j][1];
	myQueue.tail->transfers[j].amount = toAdd[j][0];
      }

      myQueue.tail->next = malloc(sizeof(struct request));
      //   myQueue.tail->next->starttime = malloc(sizeof(struct timeval));
      myQueue.tail->num_transfers = j;
      myQueue.tail = myQueue.tail->next;
      myQueue.tail->next = NULL;
      myQueue.num_jobs += 1;
    }
   }else {
     if(canAdd == 0) {
       printf("INVAILD TRANS REQUEST\n");//
     }
     else {
       printf("didn't add\n");
     }
   }
  }
  else if(strcmp(myToken, "CHECK") == 0) {
    int test = isCheckValid(myToken);
    if(test != -1 && canAdd == 0) {
      if(myQueue.num_jobs == 0) {
	//	myQueue.head->starttime = malloc(sizeof(struct timeval));
	gettimeofday(&myQueue.head->starttime, NULL);
	myQueue.head->request_id = ID;
	myQueue.head->check_acc_id = test;
	myQueue.head->transfers = malloc(sizeof(struct transfer));
	myQueue.head->num_transfers = 0; /*Set to zero because not a transfer request*/
	myQueue.head->next = myQueue.tail;
        myQueue.tail->next = NULL;
        myQueue.num_jobs += 1;
	myQueue.tail->next = malloc(sizeof(struct request));
	//	myQueue.tail->next->starttime = malloc(sizeof(struct timeval));
      }else {
	//	myQueue.tail->starttime = malloc(sizeof(struct timeval));
	gettimeofday(&myQueue.tail->starttime, NULL);
	myQueue.tail->request_id = ID;
        myQueue.tail->check_acc_id = test;
	myQueue.tail->transfers = malloc(sizeof(struct transfer));
	/*---------------------*/
	myQueue.tail->next = malloc(sizeof(struct request));
	//	myQueue.tail->next->starttime = malloc(sizeof(struct timeval));
        myQueue.tail->num_transfers = 0; /*Set to zero because not a transfer request*/
        myQueue.tail = myQueue.tail->next;
        myQueue.tail->next = NULL;
        myQueue.num_jobs += 1;
      }
    }else {
      if(canAdd == 0) {
	printf("INVALID CHECK REQUEST\n");//
      }
      else {
	printf("didn't add\n");
      }
    }
  }
  else if(strcmp(myToken, "END") == 0) {
    canAdd = 1;
  }
  else {
    printf("Error: unknown banking command\n");
  }
  //  ID += 1;
  return myQueue;
}

/*This method returns the highest thing on the queue. If queue is empty it returns NULL. It then moves the the queue "linked list" one position to the right*/
struct request * removeRequest() {
  struct request * toReturn;
  if(myQueue.num_jobs > 0) {
    toReturn = myQueue.head;
    myQueue.head = myQueue.head->next;
    myQueue.num_jobs -= 1;
    // printf("Removed an item\n");
    return toReturn;
  }
  else {
    printf("Nothing to Remove\n");
  }
}

struct queue getQueue() {
  return myQueue;
}

/*Creates the Queue and allocates memory for the head, tail and then sets the number of jobs to zero and the max account number*/
void initQueue(int maxAccountNum) {
  myQueue.head = malloc(sizeof(struct request));
  myQueue.tail = malloc(sizeof(struct request));
  myQueue.num_jobs = 0;
  maxAccount = maxAccountNum;
  // return myQueue;
}

/*This function checks to see if the TRANS request is valid by seeing if there is a maximum of 10 transactions, if there are any negative account numbers if an account
Number is greater than the max account number or if there is mutilpe transactions to the same account. If true it returns 1 and puts the account ID and amount inside an array to 
be stored in the queue otherwise it returns -1*/
int isTransValid(char * toCheck) {
  toCheck = strtok(NULL, " \n");
  int accountArr[10];
  int accountVal[10];
  int size = 0, i = 0, j = 0;
  int toReturn = 1;
  int accountNum;
  while(toCheck != NULL) {
    accountNum = atoi(toCheck);
    if(accountNum < 0 && size % 2 == 0 || accountNum > maxAccount && size % 2 == 0) {
      toReturn = -1;
    }
    if(size % 2 == 0 && size < 20) {
      int temp = size/2;
      accountArr[temp] = accountNum;
      for(i = 0; i <= temp; i++) {
	for(j = 0; j <= temp; j++) {
	  if(i != j) {
	    if(accountArr[i] == accountArr[j]) {
	      toReturn = -1;
	    }
	  }
	}
      }
    }else {
      accountVal[size/2] = accountNum;
    }    
    toCheck = strtok(NULL, " \n");
    size += 1;
  }
  if(size % 2 != 0 || size > 20) {
    toReturn = -1;
  }else{
    for(i = 0; i < size/2; i++) {
      toAdd[i][0] = accountArr[i];
      toAdd[i][1] = accountVal[i];
    }
    num_trans = size/2;
  }

  return toReturn;
}

/*This method returns -1 if the account number to check is not valid otherwise returns the number of the account;*/
int isCheckValid(char * toCheck) {
  int toReturn = 1;
  toCheck = strtok(NULL, " \n");
    int temp_id = atoi(toCheck);
    if(temp_id < 0 || temp_id > maxAccount) {
      toReturn = -1;
      return toReturn;
    }
    toCheck = strtok(NULL, " \n");
    if(toCheck != NULL) {
      toReturn = -1;
    }else {
      toReturn = temp_id;
    }

    return toReturn;
}

int accepting() {
  //  printf("CanAdd = %d\n");
  return canAdd;
}
