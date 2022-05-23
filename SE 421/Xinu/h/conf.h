/* conf.h (GENERATED FILE; DO NOT EDIT) */

#define	NULLPTR	(char *)0

/* Device table declarations */
struct	devsw	{			/* device table entry */
	int	dvnum;
	char	*dvname;
	int	(*dvinit)();
	int	(*dvopen)();
	int	(*dvclose)();
	int	(*dvread)();
	int	(*dvwrite)();
	int	(*dvseek)();
	int	(*dvgetc)();
	int	(*dvputc)();
	int	(*dvcntl)();
	int	dvcsr;
	int	dvivec;
	int	dvovec;
	int	(*dviint)();
	int	(*dvoint)();
	char	*dvioblk;
	int	dvminor;
	};

extern	struct	devsw devtab[];		/* one entry per device */


/* Device name definitions */

#define	CONSOLE     0			/* type tty      */
#define	TTY1        1			/* type tty      */
#define	TTY2        2			/* type tty      */
#define	TTY3        3			/* type tty      */
#define	DISK0       4			/* type dsk      */
#define	ETHER       5			/* type eth      */
#define	INTERNET    6			/* type dgm      */
#define	DGRAM1      7			/* type dg       */
#define	DGRAM2      8			/* type dg       */
#define	DGRAM3      9			/* type dg       */
#define	DGRAM4      10			/* type dg       */
#define	RFILSYS     11			/* type rfm      */
#define	RFILE1      12			/* type rf       */
#define	RFILE2      13			/* type rf       */
#define	RFILE3      14			/* type rf       */
#define	RFILE4      15			/* type rf       */
#define	FILE1       16			/* type df       */
#define	FILE2       17			/* type df       */
#define	FILE3       18			/* type df       */
#define	FILE4       19			/* type df       */
#define	NAMESPACE   20			/* type nam      */

/* Control block sizes */

#define	Ntty	4
#define	Ndsk	1
#define	Ndf	4
#define	Neth	1
#define	Ndgm	1
#define	Ndg	4
#define	Nrfm	1
#define	Nrf	4
#define	Nnam	1

#define	NDEVS	21

/* Declarations of I/O routines referenced */

extern	int	ttyinit();
extern	int	ttyopen();
extern	int	ionull();
extern	int	ttyread();
extern	int	ttywrite();
extern	int	ioerr();
extern	int	ttycntl();
extern	int	ttygetc();
extern	int	ttyputc();
extern	int	ttyiin();
extern	int	ttyoin();
extern	int	dsinit();
extern	int	dsopen();
extern	int	dsread();
extern	int	dswrite();
extern	int	dsseek();
extern	int	dscntl();
extern	int	dsinter();
extern	int	ethinit();
extern	int	ethread();
extern	int	ethwrite();
extern	int	ethinter();
extern	int	dgmopen();
extern	int	dgmcntl();
extern	int	dginit();
extern	int	dgclose();
extern	int	dgread();
extern	int	dgwrite();
extern	int	dgcntl();
extern	int	rfopen();
extern	int	rfcntl();
extern	int	rfinit();
extern	int	rfclose();
extern	int	rfread();
extern	int	rfwrite();
extern	int	rfseek();
extern	int	rfgetc();
extern	int	rfputc();
extern	int	lfinit();
extern	int	lfclose();
extern	int	lfread();
extern	int	lfwrite();
extern	int	lfseek();
extern	int	lfgetc();
extern	int	lfputc();
extern	int	naminit();
extern	int	namopen();


/* Configuration and Size Constants */

#define	NPROC	    10			/* number of user processes	*/
#define	NSEM	    30			/* number of semaphores		*/
#define	MEMMARK				/* define if memory marking used*/
#define	RTCLOCK				/* system has a real-time clock	*/
#define	STKCHK				/* resched checks stack overflow*/
#define	NETDAEMON			/* Ethernet network daemon runs	*/
#define	GATEWAY	     128,10,2,70	/* Gateway address		*/
#define	TSERVER	    "128.10.2.3:37"	/* Time server address		*/
#define	RSERVER	    "128.10.2.3:2001"	/* Remote file server address	*/
#define	NSERVER	    "128.10.2.3:53"	/* Domain Name server address	*/
#define	VERSION	    "7.6 (01/13/87)"	/* label printed at startup	*/
					/* (vers7: Diskless Workstation)*/
