/*******************************************************************************
 * *
 * * CprE 308 Scheduling Lab
 * *
 * * scheduling.c
 * * last updated 2020-03-01 - mlw
 * *******************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define NUM_PROCESSES 20
#define NUM_SCHEDULERS 4

#define SCHEDULER_NAME_LEN 30

/* Defines a simulated process */
typedef struct process {
  int arrivaltime;    /* Time process arrives and wishes to start */
  int starttime;      /* Time process actually starts */
  int runtime;        /* Time process requires to complete job */
  int remainingtime;  /* Runtime remaining before process completes */
  int endtime;        /* Time process finishes */
  
  int priority;       /* Priority of the process */

  int running;        /* Convenience - indicates if the process is currently running */
  int finished;       /* Convenience - indicates if the process has finished */
} process;

/* Defines a simulated scheduler */
typedef struct scheduler
{
  int (* func) (const process [], int); /* Function to run for the scheduler */
  char name[SCHEDULER_NAME_LEN];  /* Name of the scheduler, for pretty printing */
} scheduler;

/* Prototypes of scheduler functions */
int first_come_first_served(const process proc[], int t);
int shortest_remaining_time(const process proc[], int t);
int round_robin(const process proc[], int t);
int round_robin_priority(const process proc[], int t);


/*-------------------------Static data----------------------------------*/
int last[NUM_PROCESSES]; /*Round Robin process queue*/
int queueIndex = 0, aSize = 0; /*Round Robin queue position and building size of array*/ 
int priority[NUM_PROCESSES]; /*Round Robin priority process queue*/
int pIndex = 0, pSize = 0; /*Holds the position of the position of the priority queue and size of array*/

/* Main, contains most of the simulation code */
int main() {
  int i,j;
  process proc[NUM_PROCESSES],        /* List of processes */
            proc_copy[NUM_PROCESSES]; /* Backup copy of processes */
            
  /* Initialize the schedulers */
  scheduler schedulers[NUM_SCHEDULERS];
  schedulers[0].func = first_come_first_served;
  strncpy(schedulers[0].name, "First come first served", SCHEDULER_NAME_LEN);
  schedulers[1].func = shortest_remaining_time;
  strncpy(schedulers[1].name, "Shortest remaining time", SCHEDULER_NAME_LEN);
  schedulers[2].func = round_robin;
  strncpy(schedulers[2].name, "Round robin", SCHEDULER_NAME_LEN);
  schedulers[3].func = round_robin_priority;
  strncpy(schedulers[3].name, "Round robin with priority", SCHEDULER_NAME_LEN);

  /* Seed random number generator */
  //srand(time(0));    /* Use this seed to test different inputs */
  srand(0xC0FFEE);     /* Use this seed to test fixed input */

  /* Initialize process structures */
  for(i=0; i<NUM_PROCESSES; i++)
  {
    proc[i].arrivaltime = rand() % 100;
    proc[i].runtime = (rand() % 30) + 10;
    proc[i].remainingtime = proc[i].runtime;
    proc[i].priority = rand() % 3;
	
    proc[i].starttime = -1;
    proc[i].endtime = -1;
    proc[i].running = 0;
    
    proc[i].finished = 0;
  }

  /* Print process values */
  printf("Process\tarrival\truntime\tpriority\n");
  for(i = 0; i < NUM_PROCESSES; i++) {
    printf("%d\t%d\t%d\t%d\n", i, proc[i].arrivaltime, proc[i].runtime,
           proc[i].priority);
  }
  
  /* Run simulation for each scheduler */
  for(i = 0; i < NUM_SCHEDULERS; i++) {
    int num_finished = 0;
    int current_time = 0;
    int prev_pid = -1;
    int total_turnaround_time = 0;
    printf("\n\n--- %s\n", schedulers[i].name);
    
    /* Use a copy of the processes array */
    memcpy(proc_copy, proc, NUM_PROCESSES * sizeof(process));
    
    /* Run the simulation until all processes finish */
    while(num_finished < NUM_PROCESSES) {
      process * p;
      
      /* Call the scheduler */
      int next_pid = schedulers[i].func(proc_copy, current_time);
      
      /* If there's no process to run, just move time forward */
      if(next_pid < 0) {
        current_time += 1;
        continue;
      }
      
      /* Convenience - use p to reference the proc */
      p = &proc_copy[next_pid];
      
      /* If the process just started, print a message */
      if(p->starttime == -1) {
        printf("%03d: Process %d started\n", current_time, next_pid);
        p->starttime = current_time;
      }
      
      /* Update which process is running */
      if(prev_pid >= 0) {
        proc_copy[prev_pid].running = 0;
      }
      prev_pid = next_pid;
      p->running = 1;
      
      /* Move time forward */
      current_time += 1;
      
      /* Update remaining time of the process that just ran */
      p->remainingtime -= 1;
      
      /* If the process already finished, it shouldn't have been chosen */
      if(p->remainingtime < 0) {
        printf("Error: tried to run finished process %d\n", next_pid);
        continue;
      /* If the process just finished, print a message and do bookkeeping */
      } else if(p->remainingtime == 0) {
        printf("%03d: Process %d finished\n", current_time, next_pid);
        p->endtime = current_time;
        p->finished = 1;
        num_finished += 1;
        /* Keep a running total of turnaround time */
        total_turnaround_time += p->endtime - p->arrivaltime;
      }
    }
    
    /* Print average turnaround time */
    printf("Average turnaround time: %.01lf seconds\n", (double)total_turnaround_time / NUM_PROCESSES);
  }

  return 0;
}

/* ----------------------Schedulers------------------------------*/
/* Each scheduler function should return the index of the process 
   from proc[] that should run next. Parameter t is the current time 
   of the simulation. Note that proc[] contains all processes, 
   including those that have not yet arrived and those that are finished.
   You are responsible for ensuring that you schedule a "ready"
   process. If no processes are ready, return -1.
*/
   
int first_come_first_served(const process proc[], int t)
{
  /* TODO: Implement scheduling algorithm here */
  int i, found =0; /*Found will determine if the smallest ready process has been found*/
  int smallest = t; /*the current time*/
  int index = -1; /*the index of the process that needs to run*/

  for(i = 0; i < NUM_PROCESSES; i++) { 
    if(proc[i].arrivaltime <= smallest && proc[i].finished != 1) { /*Checks to see if the process has arrived/ready and isn't finished*/
      if(proc[i].arrivaltime == smallest) { /*if the process is equal to the arrival time and the smallest hasn't been found yet*/
	if(found != 1) {
	  smallest = proc[i].arrivaltime; /*smallest will be set to the current smallest time*/
	  index = i; /*index to return will be set to the smallest process*/
	}
      }else {
	smallest = proc[i].arrivaltime; /*Smallest will be set to the smallest arrivaltime*/
	index = i; /*index will be set to the smallest arrivaltime process*/
	found = 1; /*Found a smallest time*/
      }
    }
  }

  if(index != -1) {
    return index; /*if the index isn't -1 it has found a smalest and returns it*/
  }else {
    return -1; /*Otherwise it returns -1 for no processes*/
  }
}

int shortest_remaining_time(const process proc[], int t)
{
  /* TODO: Implement scheduling algorithm here */
  int i, index = -1; /*Index to return the process to return*/
  int current = -1; /*current process to return*/
  for(i = 0; i < NUM_PROCESSES; i++) {
    if(proc[i].arrivaltime <= t && proc[i].finished != 1) { /*Only checks to return a process if their arrival time is less than the current and it hasn't finished yet*/
      if(current == -1) { /*If there is no current yet*/
	current = proc[i].remainingtime; /*sets current to the remaining time left for the first process found that is ready from the process array*/
	index = i; /*sets index to be the smallest current remaining time left*/
      }
      if(proc[i].remainingtime < current) { /*if the next ready process from the array has a smaller remaining time left it updates current and the index*/
	current = proc[i].remainingtime;
	index = i;
      }
    }
  }
  return index; /*Returns the index of the smallest reamining time left*/
}

int round_robin(const process proc[], int t)
{
  /* TODO: Implement scheduling algorithm here */
  //HINT: consider using a static variable to keep track of the previously scheduled process
  last[aSize] = -1; /*Sets the item at the size of the array to be -1*/
  int i, index = 0, pos, found = -1; /*Index : index to return, found : will dictate if the processes is already in the queue*/
  for(i = 0; i < NUM_PROCESSES; i++) {
    found = -1;/*Resets every iteration of the process array*/
    if(proc[i].arrivaltime <= t && proc[i].finished != 1) { /*Only adds things to the queue if the arrival time is left than current time and the process isn't finished*/
      for(pos = 0; pos <= aSize; pos++) { /*Loops through the queue array*/
	if(i == last[pos]) { /*Checks to see if the process to add is already in the array*/
	  found = 1; /*If it is in the queue will set found to be 1*/
	}
      }
      if(found == -1) { /*Only adds things to the queue if found is equal to -1*/
	last[aSize++] = i; /*Puts the process inside the array and then increments the size of the array*/
      }
    }
  }
  if(queueIndex == aSize) { /*if the position of the queue has reached the end it reset the position to the beginning*/
    queueIndex = 0;
  }

  index = last[queueIndex++]; /*index will be set to the process in the queue array array then increment the position of the queue Index*/
  int count = 0; /*Variable which will determine if there are still ready processes*/

  while(proc[index].finished != 0) { /*Runs while the processes to return is finished*/
    index = last[++queueIndex]; /*increments the queue Index and then set the index to the processes from the queue array*/
    count += 1; /*increments the count*/
    if(queueIndex == aSize) { /*if the end is reached reset the queue position to the beginning*/
      queueIndex = 0;
    }
    if(count >= 20) { /*if the count has reached the number of processes then their are no more ready processes*/
      index = -1; /*sets the index to be -1 and breaks from the loop*/
      break;
    }
  }
  return index;
}

int round_robin_priority(const process proc[], int t)
{
  /* TODO: Implement scheduling algorithm here */
  priority[pSize] = -1; /*Fills in the array at pSize with a value so not checking against NULL*/
  int i, j,  pos, index, found = -1, next; /*Index : index of the process to run Found: determines if the process is already in the priority queue Next: will be the next process in the queue*/
  for(i = 0; i < NUM_PROCESSES; i++) { /*Loops through the process array*/
    found = -1; /*Resets to negative 1 every iteration of the loop*/
    if(proc[i].arrivaltime <= t && proc[i].finished != 1) { /*WIll only attempt to add processes that are ready and aren't finished*/
      for(pos = 0; pos <= pSize; pos++) { /*Loops through the priority queue*/
	if(i == priority[pos]) { /*if the item to add to the queue is already in the queue it sets found to be 1 or "true"*/
	  found = 1;
	}
      }
      if(found == -1) { /*Only adds items to the queue if it isn't already in there and then increments the "growing" size of the array after*/
	priority[pSize++] = i;
      }
    }
  }
  
  int count = 0; /*Will keep track to ensure the it only goes through the size of the priority queue*/
  if(pIndex == pSize) { /*If the priority queue index equals the size of the array then the position of the index is reset to the beginning*/
    pIndex = 0;
  }
  index = priority[pIndex++]; /*set the index to be the processes from the queue and the queue index and the increments the queue index after*/

  /*Runs only for the amount of processes inside the queue*/
  while(count < pSize) {
    if(pIndex == pSize) { /*If the priority queue index is at the end reset it to the beginning*/
      pIndex = 0;
    }
    /*Check to see if the process to return is finished*/
    while(proc[index].finished == 1) {
      index = priority[pIndex++]; //if finished set index and increment
      /*Check to make sure current position of queue isn't at the end*/
      if(pIndex == pSize) {
	pIndex = 0; //if at the end reset it 
      }
    }
    int next = priority[pIndex++]; //set next to be the current index of queue and then incremenet
    /*Check to see if the "next" process is finished*/
    while(proc[next].finished == 1) {
      if(pIndex == pSize) { //checks to make sure the current position isn't at the end before setting a new next
	pIndex = 0;
      }
      next = priority[pIndex++];
    }
    /*Check to see if the next processes has aa higher priority than the current (index) */
    if(proc[next].priority > proc[index].priority ) {
      index = next;
      
    }
    count += 1; //incrementing the count to ensure the loop doesn't run forever
  }
  return index;
}

