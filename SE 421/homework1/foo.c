int foo(char* courseName) {
	char *name = NULL;
	char *vaildCourseName = "SE-421";
	if(courseName != NULL) {
		name = malloc(strlen(courseName) + 1));
	}else {
		goto err;
	}
	if(name!=NULL) {
		strcpy(name, courseName);
	}else {
		goto err;
	}
	if(strcmp(name, validCourseName)) {
		goto invalid;
	}
	free(name);
	name = NULL;
	free(courseName);
	courseName = NULL;
	reutrn 1;
	err: printf("Course name is NUll\n");
	goto exit;
	invalid: printf("Course name is not valid\n");
	exit: return -1;
}