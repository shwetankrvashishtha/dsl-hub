/**
 * @author shwetankvashishtha
 *
 */

 def signature = 'new groovy.json.JsonSlurperClassic'
 org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get().approveSignature(signature)

 return freeStyleJob('Slave-Health-Check') {
 	steps {
 		int exitcode = 0
 		for (android_slaves in hudson.model.Hudson.instance.slaves) {
 			if (android_slaves.getComputer().isOffline().toString() == "true"){
 				println('Slave ' + android_slaves.name + " is offline!"); 
 				exitcode++;
 			}
 		}

 		if (exitcode > 0){
 			println("We have Slave down, failing the build!");
 			return 1;
 		}
 	}	
 }

