/*----------------Include Statements ---------------------------------------------- */
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
/* ----------------Prototypes ------------------------------------------------------
   ------------------------------------------------------------------------------*/
/*This function will either default to user prompt or change based on the users
  input*/
void  setPrompt(int count, char * inputs[], char prompt[]);

/*This function will prompt the user to enter a command and then return the sting and then return the command*/
void enterCommand(char prompt[]);

/*This function will handle different Built-in and Non Built in commands requested by the user*/
void commandHandling(char * request);

/*This function will handle processes whether a function needs to run in the background or foreground*/
void processCreation(char * request);

/*This function will create background and foreground functions and handle requirements*/
void programCreation(char * command, char * attributes[], int isBackground);
/*------------------Static data--------------------------------------------------------
---------------------------------------------------------------------------------*/
char userCommand[100] = "null"; /* This will hold the users command that is entered */
int count = 0; /* keeps track of the size and position inside the char * array while parsing data*/

int main(int argc, char * argv[]) {
  bool myStatus = true; /*condition for the while loop*/
  char prompt[100] = "308sh>"; /*default user prompt */
  setPrompt(argc, argv, prompt);

  while(myStatus) {
      enterCommand(prompt);
      char *  myToken = strtok(userCommand, " "); /*prases the data using a whitespace and a new line as the delimter*/
      if(strcmp(myToken, "\n") == 0) { /*This checks against the user entering in just a new line and in that case doesnt do anything but prompt them again. Not all the built in commands use strncmp which will handle the newline at the end of the users input*/
	continue;
      }
      commandHandling(myToken);
  }
      return 0; 
}

/* This function sets the users prompt by checking if "count" > 2 which is a arguement that argc is passed in through. If count is greater than 2 in the checks to see if the second item of inputs which argv is passed in through is equal to -p. If so it sets prompt equal to the 3 item of inputs and then adds a ">" followed by a null character. if count is not greater than 2 this function doesn't do anything */
void setPrompt(int count, char * inputs[], char prompt[]) {
  char uInputs[] = "null";
  if(count > 2) {
    strcpy(uInputs, inputs[1]);
    if(strcmp(uInputs, "-p") == 0) {
      strcpy(prompt, inputs[2]);
      strcat(prompt, ">\0");
    }
  }
}

void enterCommand(char prompt[]) {
  printf("%s ", prompt); /*prints out the users prompt with a space*/
  fgets(userCommand, 100, stdin); /*Request for the user input which will be stored in the static data variable userCommand*/
}

void commandHandling(char * request) {
  if(strncmp(request, "exit", 4) == 0) { //checks to see if the first parsed input from the user equals exit
    request = strtok(NULL, " \n");
    if(NULL == request) {
      exit(0); //sucessfully exits the program
    }else {
      printf("Error: too many arguements entered\n");
    }
  }
  else if(strcmp(request, "pid") == 0) {       /*Checks to see if the first parsed input equlas pid*/
    request = strtok(NULL, " \n");            /*calls strtok again which will either return null for nothing left or then next input */
    if(request == NULL) {                    /*if request becomes null the process ID is printed*/
       printf("Process ID is: %d\n", getpid());
    }else {                                         /*If it does not equal null it doesn't print out the PID because of too many arguements */
      printf("Error: too many arguements entered\n");
    }
  }
  else if(strncmp(request, "ppid", 4) == 0) {       /*Checks to see if the parsed input equals ppid*/
    request = strtok(NULL, " \n");                 /*Calls strtok again to see if there is anymore input*/
    if(request == NULL) {                         /*If the prior call to strtok was null then it prints out the ppid*/
      int ppid = getppid();
      printf("Parent process ID is: %d\n", ppid);
    }else {                                        /*If the result of strtok was not equal to null itt notifies the user that they used too many arguements*/
      printf("Error: too many arguements entered\n");
    }
  }
  else if(strncmp(request, "cd", 2) == 0){            /*Checks to see if the first parsed user input equals cd*/
    request = strtok(NULL, " \n");                   /*Calls strtok to get the next parsed input that the user initially entered*/
    int test;                                       /*vairable to tell if changing the directory was successful or not*/
    if(NULL == request) {                          /*If the result is NULL it changes the directory to HOME by getting the environment varaible for home*/
      test = chdir(getenv("HOME"));
    }else {                                      /*If the result of strtok is not NULL it uses that as the directory to be changed to*/
      test = chdir(request);
    }
    if(test == -1) {                            /*If the result of the users requested directory change returns negative one it alerts the user that there is no such file or directory*/
      perror("cd");
    }
  }
  else if(strncmp(request, "pwd", 3) == 0) {             /*Checks to see if the first user parsed input is equal to pwd*/
    request = strtok(NULL, " \n");                      /*Calls strtok again to get the next input from the initial user input*/
    if(request == NULL) {                              /*if the call to strtok returns null then it prints the users current working directory*/
      char pwd[256];
      printf("%s\n", getcwd(pwd, sizeof(pwd)));
    }else {                                          /*If the call to strtok doesn't equal null it notifies the user that they had too many arguements*/
      printf("Error: too many arguements entered\n");
    }
  }
  else {
    processCreation(request); /*If none of the conditional statments are taken from above then it is not a built in command and will be handled through the function which will pass in request command*/
  }                           /*The command being the first parsed input from the user*/
}


void processCreation(char * request) {
  char * command;                    /*Varriable that will be used for the command arguement of execvp*/
  command = request;                 /*sets the first user parsed input to command*/

  int count = 0, bg = 0;                 /*count keeps track of the position in the array "arguements" and bg will be */
  char * arguements[25];                /*array that will hold the attributs for the command in execvp*/
  arguements[0] = request;             /*First puts in the command and then loops through while putting in every other user parsed input until NULL is found but doesn't put in NULL*/
  while(arguements[count] != NULL) {
    arguements[++count] = strtok(NULL, " \n");
  }

  if(strncmp(arguements[count-1], "&\n", 1) == 0) { /*if the second to last position equals "&" it sets it equals to NULL, sets bg = 1*/
    arguements[count-1] = NULL;
    bg = 0; /*couldn't get background processes to work all the way*/
  }

  programCreation(command, arguements, bg); /*Function will create foreground or background processes*/
}

void programCreation(char * command, char * attributes[], int isBackground) {
int currentPid = 0, status = 0, test = 0;
 currentPid  = fork();                        /*creates a new process (child) and (parent)*/
  /*this conditional will not be taken due to isBackground always being 1 */
  if(isBackground == 1) {
     if(currentPid == 0) {
       currentPid = getpid();
       printf("[%d] %s\n", currentPid, attributes[0]);
       test = execvp(command, attributes);
       if(test == -1) {
	 perror("");
	 exit(255);
       }
     }
     else{
       waitpid(-1, &status, 0);
       if(WIFEXITED(status)) {
	 printf("[%d] %s Exit %d\n", currentPid, attributes[0], WEXITSTATUS(status));
       }
     }
  }else {
    if(currentPid == 0) {                                                /*if it is the child process it will set currentPid to the childs pid*/ 
	  currentPid = getpid();
	  printf("[%d] %s\n", currentPid, attributes[0]);                  /*Prints out the creation of the child process with PID and name*/
	  test = execvp(command, attributes);                             /*Changes the child process to the specified command with the attributes*/
	  if(test == -1) {                                               /*if the processes of creating a new child fails it will stop the child process and print out and error*/
	    fprintf(stderr,"Cannot exec %s: ", attributes[0]);
	    perror("");
	    exit(255);
	  }
	}
    else { /*If the pid is equal to the parent it waits for the child to exit*/
	wait(&status);
	if(WIFEXITED(status)) {
	  printf("[%d] %s Exit %d\n", currentPid, attributes[0], WEXITSTATUS(status)); /*Prints out the child that ended with its PID and exit status*/
	}
    }
  }

}
