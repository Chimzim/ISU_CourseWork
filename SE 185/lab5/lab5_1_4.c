/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>

/* This program calculates the energy of one photon of uder inputed wavelength
 * of light */

int main()
{
    double wave_length=0; //changed the - to an underscore and updated all other occurances
    double length_in_meters=0; //deleted weird symbol infront of length_in_meters and updated all other occurances
    double energy =0; //deleted all zeros to match up with energy call in line 36 in print statement

    const planck_const = (6.62606957)*(pow(10,-34)); //Planck's constant (let them be known as constants and deleted conflicting decleration above)
    const speed_light = (2.99792458)*(pow(10,8)); //Constant for the speed of light (let them be known as constants and deleted conflicting dec above)
   

    printf("Welcome! This program will give the energy, in Joules,\n");
    printf("of 1 photon with a certain wavelength.\n");
    printf("Please input a wavelength of light in nano-meters.\n");
    printf("Please do not enter a negative, or zero, wavelength.\n");

    scanf("%lf", &wave_length);

    if (wave_length > 0.0)
    {
        length_in_meters = wave_length / pow(10,9); //Converting nano-meters to meters
        energy = (planck_const*speed_light) / (length_in_meters); //Calculating the energy of 1 photon
        printf("A pha wavoton with elength of %8.3lf nano-meters, carries\n%30.25lf joules of energy.", wave_length, energy);
    }
    else
    {
        printf("Sorry, you put in an invalid number.");
        printf("Please rerun the program and try again.");
    }

    return 0;

}
