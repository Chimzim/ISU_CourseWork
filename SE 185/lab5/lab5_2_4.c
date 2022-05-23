/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This program calculates values of resistances, voltages, or current
 * using Ohm's Law */

double voltage(double resistance, double current);

double resistance(double voltage, double current);

double current(double voltage, double resistance);

int main()
{
    int select = 0;
    // int v, i, r; changed the declartion of the variable to a double since it was everywhere else 
	double v, i, r;

    printf("Select:\n1 for voltage\n2 for resistance\n3 for current\n");

    scanf("%d", &select);

    if(select > 3 || select < 1)
    {
        printf("Invalid number\n");
        return -1;
    }

    printf("Enter floating point numbers for input...\n");
    if(select == 1)
    {
        printf("Please enter a resistance value: ");
        scanf("%lf", &r);

        printf("Please enter a current value: ");
        scanf("%lf", &i);

        printf("Your voltage is: %lf Volts\n", voltage(r, i));
    }
    else if(select == 2)
    {
        printf("Please enter a voltage value: ");
        scanf("%lf", &v);

        printf("Please enter a current value: ");
        scanf("%lf", &i);

        printf("Your Resistance is: %lf Ohms\n", resistance(v, i));

    }
    else if(select == 3)
    {
        printf("Please enter a resistance value: ");
        scanf("%lf", &r);

        printf("Please enter a voltage value: ");
        scanf("%lf", &v);

        printf("Your current is: %lf Amps\n", current(v, r));


    }

    return 0;

}





double voltage(double resistance, double current)
{
    return resistance * current;
}

double resistance(double voltage, double current)
{
    return voltage / current;
}

double current(double voltage, double resistance)
{
    return voltage / resistance;
}
