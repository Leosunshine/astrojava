import java.io.*;
import java.lang.*;
import java.text.*;
import java.util.*;

import com.obliquity.astronomy.*;

public class JPLEphemerisSpeedTester {
    public static void main(String[] args) {
	if (args.length < 2) {
	    System.err.println("Usage: JPLEphemerisSpeedTester filename count");
	    System.exit(1);
	}

	String filename = args[0];
	int nTests = Integer.parseInt(args[1]);

	JPLEphemeris ephemeris = null;

	try {
	    FileInputStream istream = new FileInputStream(filename);
	    ObjectInputStream ois = new ObjectInputStream(istream);
	    ephemeris = (JPLEphemeris)ois.readObject();
	    ois.close();
	}
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("IOException when de serialising ephemeris ... " + ioe);
 	    System.exit(1);
	}
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("ClassNotFoundException when de serialising ephemeris ... " + cnfe);
 	    System.exit(1);
	}

	Random random = new Random();
	
	double []pos = new double[3];
	double []vel = new double[3];

	double tEarliest = ephemeris.getEarliestDate();
	double tLatest   = ephemeris.getLatestDate();

	double tSpan = tLatest - tEarliest;

	long startTime = System.currentTimeMillis();

	for (int j = 0; j < nTests; j++) {
	    int nBody = random.nextInt(JPLEphemeris.SUN);
	    double t = tEarliest + tSpan * random.nextDouble();

	    try {
		ephemeris.calculatePositionAndVelocity(t, nBody, pos, vel);
	    }
	    catch (JPLEphemerisException jee) {
		jee.printStackTrace();
		System.err.println("JPLEphemerisException from first ephemeris object ... " + jee);
		System.exit(1);
	    }
	}

	long finishTime = System.currentTimeMillis();
	long dt = finishTime - startTime;

	System.out.println("Completed " + nTests + " test calculations in " + dt + " milliseconds");
    }
}
