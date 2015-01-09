package org.integrallis.greeting;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.integrallis.greeting.Person.Education.*;
import static org.integrallis.greeting.Person.Gender.*;
import static org.integrallis.greeting.Person.MaritalStatus.*;

/**
 * This is a sample class to launch a rule.
 */
public class GreetingsExample {

    public static final void main(String[] args) {
    	    KieSession kSession = null;
        try {
            // load up the knowledge base
        	    
        	    // 1. - Get the KieServices singleton factory
	        KieServices ks = KieServices.Factory.get();
	        
	        // 2. - Get KieContainer from the KieServices factory
    	        KieContainer kContainer = ks.getKieClasspathContainer();
    	        
    	        // 3. - Build a new KieSession
            kSession = kContainer.newKieSession("ksession-rules");

            // 4. - Assert some Facts (Person/TimeOfDay objects)
 			Person stephenFalken = new Person("Stephen Falken", MALE, 48, SINGLE, DROPOUT);
 			Person richie = new Person("Richie Rich", MALE, 9, SINGLE, NONE);
 			Person marieCurie = new Person("Marie Curie", FEMALE, 45, MARRIED, PHD);
 			
 			TimeOfDay nineAm = new TimeOfDay(9, 0);

 			kSession.insert(marieCurie);
 			kSession.insert(stephenFalken);
 			kSession.insert(richie);
 			kSession.insert(nineAm);
            
            // 5. - Fire the Rules
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
          	kSession.dispose();
        }
    }

}
