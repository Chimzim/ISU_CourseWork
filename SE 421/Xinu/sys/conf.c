/* conf.c (GENERATED FILE; DO NOT EDIT) */

#include <conf.h>

/* device independent I/O switch */

struct	devsw	devtab[NDEVS] = {

/*  Format of entries is:
device-number, device-name,
init, open, close,
read, write, seek,
getc, putc, cntl,
device-csr-address, input-vector, output-vector,
iint-handler, oint-handler, control-block, minor-device,
*/

/*  CONSOLE  is tty  */

0, "CONSOLE",
ttyinit, ttyopen, ionull,
ttyread, ttywrite, ioerr,
ttygetc, ttyputc, ttycntl,
027745435324, 027745435324, 027745435324,
ttyiin, ttyoin, NULLPTR, 0,

/*  TTY1  is tty  */

1, "TTY1",
ttyinit, ttyopen, ionull,
ttyread, ttywrite, ioerr,
ttygetc, ttyputc, ttycntl,
027745435324, 027745435324, 027745435324,
ttyiin, ttyoin, NULLPTR, 1,

/*  TTY2  is tty  */

2, "TTY2",
ttyinit, ttyopen, ionull,
ttyread, ttywrite, ioerr,
ttygetc, ttyputc, ttycntl,
027745435324, 027745435324, 027745435324,
ttyiin, ttyoin, NULLPTR, 2,

/*  TTY3  is tty  */

3, "TTY3",
ttyinit, ttyopen, ionull,
ttyread, ttywrite, ioerr,
ttygetc, ttyputc, ttycntl,
027745435324, 027745435324, 027745435324,
ttyiin, ttyoin, NULLPTR, 3,

/*  DISK0  is dsk  */

4, "DISK0",
dsinit, dsopen, ioerr,
dsread, dswrite, dsseek,
ioerr, ioerr, dscntl,
027745435324, 027745435324, 027745435324,
dsinter, dsinter, NULLPTR, 0,

/*  ETHER  is eth  */

5, "ETHER",
ethinit, ioerr, ioerr,
ethread, ethwrite, ioerr,
ioerr, ioerr, ioerr,
027745435324, 027745435324, 027745435324,
ethinter, ethinter, NULLPTR, 0,

/*  INTERNET  is dgm  */

6, "INTERNET",
ionull, dgmopen, ioerr,
ioerr, ioerr, ioerr,
ioerr, ioerr, dgmcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 0,

/*  DGRAM1  is dg  */

7, "DGRAM1",
dginit, ioerr, dgclose,
dgread, dgwrite, ioerr,
ioerr, ioerr, dgcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 0,

/*  DGRAM2  is dg  */

8, "DGRAM2",
dginit, ioerr, dgclose,
dgread, dgwrite, ioerr,
ioerr, ioerr, dgcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 1,

/*  DGRAM3  is dg  */

9, "DGRAM3",
dginit, ioerr, dgclose,
dgread, dgwrite, ioerr,
ioerr, ioerr, dgcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 2,

/*  DGRAM4  is dg  */

10, "DGRAM4",
dginit, ioerr, dgclose,
dgread, dgwrite, ioerr,
ioerr, ioerr, dgcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 3,

/*  RFILSYS  is rfm  */

11, "RFILSYS",
ioerr, rfopen, ioerr,
ioerr, ioerr, ioerr,
ioerr, ioerr, rfcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 0,

/*  RFILE1  is rf  */

12, "RFILE1",
rfinit, ioerr, rfclose,
rfread, rfwrite, rfseek,
rfgetc, rfputc, rfcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 0,

/*  RFILE2  is rf  */

13, "RFILE2",
rfinit, ioerr, rfclose,
rfread, rfwrite, rfseek,
rfgetc, rfputc, rfcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 1,

/*  RFILE3  is rf  */

14, "RFILE3",
rfinit, ioerr, rfclose,
rfread, rfwrite, rfseek,
rfgetc, rfputc, rfcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 2,

/*  RFILE4  is rf  */

15, "RFILE4",
rfinit, ioerr, rfclose,
rfread, rfwrite, rfseek,
rfgetc, rfputc, rfcntl,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 3,

/*  FILE1  is df  */

16, "FILE1",
lfinit, ioerr, lfclose,
lfread, lfwrite, lfseek,
lfgetc, lfputc, ioerr,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 0,

/*  FILE2  is df  */

17, "FILE2",
lfinit, ioerr, lfclose,
lfread, lfwrite, lfseek,
lfgetc, lfputc, ioerr,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 1,

/*  FILE3  is df  */

18, "FILE3",
lfinit, ioerr, lfclose,
lfread, lfwrite, lfseek,
lfgetc, lfputc, ioerr,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 2,

/*  FILE4  is df  */

19, "FILE4",
lfinit, ioerr, lfclose,
lfread, lfwrite, lfseek,
lfgetc, lfputc, ioerr,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 3,

/*  NAMESPACE  is nam  */

20, "NAMESPACE",
naminit, namopen, ioerr,
ioerr, ioerr, ioerr,
ioerr, ioerr, ioerr,
027745435324, 027745435324, 027745435324,
ioerr, ioerr, NULLPTR, 0
	};
