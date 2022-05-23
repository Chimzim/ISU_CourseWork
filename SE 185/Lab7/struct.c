
typedef struct studentInfo {
	char name[50];
	char classGrade;
	double averageTest;
	double exam;
	double homework, quiz, lab;
	char overallGrade;
} student;

int main() {
	student studentInfo_1;
	
	//Chimzim Ogbondah, Tyler Chappell
	
	
}

studentInfo calculateAverage(studentInfo person) {
	person.averageTest = (person.homework + person.exam + person.quiz + person.lab) / 4;
	if(person.averageTest >= 90) {
		person.overallGrade = 'A';
	}
	else if(person.averageTest >= 80) {
		person.overallGrade = 'B';
	}
	else if(person.averageTest >= 70) {
		person.overallGrade = 'C';
	}
	else if(person.averageTest >= 60) {
		person.overallGrade = 'D';
	}
	else if(person.averageTest < 60) {
		person.overallGrade = 'F';
	}
	return person;
}