/**
 * @author shwetankvashishtha
 *
 */

 def signature = 'new groovy.json.JsonSlurperClassic'
 org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get().approveSignature(signature)
 
 def branchesParam = binding.variables.get('BRANCHES')
 def branches = branchesParam ? branchesParam.split(' ') : ['BB_Task']

 def constants = [

    // generic params
    project: 'BB_Work_Auto_Android',
    component: 'SeeTest',
    branches: branches,
    name: 'Android',
    
    ]

    def friendlyProject = constants.project.capitalize()
    def friendlyComponent = constants.component.capitalize()
    def friendlyName = constants.name.capitalize()
    folder("${friendlyName}") {
        displayName("${friendlyProject}_${friendlyComponent}")
    }

    for (branch in constants.branches) {
        postflightJob(constants, branch)
    }

    def postflightJob(constants, branch) {
        def friendlyBranch = branch.capitalize()
        def friendlyLabel = constants.name.capitalize()

        return mavenJob("${constants.name}/${branch}-${constants.name}") {
        // Sets a display name for the project.
        displayName("${friendlyLabel}_${friendlyBranch}").with {
            description ' '
        }
        
        parameters {
            activeChoiceParam('xmlFile') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["Android_Tasks_Sanity", "Android_Tasks_KPI"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('apkType') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["external", "internal"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            stringParam('TNBuildNumber', '241')
            activeChoiceParam('Server') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["onPrem"]')
                    fallbackScript('return ["ERROR"]')
                }
            }  
            activeChoiceParam('OwaUrl') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["gs-ex16.gems.sw.rim.net/ews/Exchange.asmx"]')
                    fallbackScript('return ["ERROR"]')
                }
            } 
            activeChoiceParam('ExchangeServer') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["gs-ex10.gems.sw.rim.net", "gs-ex13.gems.sw.rim.net", "gs-ex16.gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }  
            activeChoiceParam('domain') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["gems"]')
                    fallbackScript('return ["ERROR"]')
                }
            }       
            activeChoiceParam('PrimaryEmail') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0116@gems.sw.rim.net", "kchhatbar0110@gems.sw.rim.net", "kchhatbar0116@gems.sw.rim.net", "kchhatbar0113@gems.sw.rim.net", "rudani0110@gems.sw.rim.net", "rudani0116@gems.sw.rim.net", "rudani0113@gems.sw.rim.net", "rpourwal0110@gems.sw.rim.net", "rpourwal0113@gems.sw.rim.net", "pppourwal0116@gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0216@gems.sw.rim.net", "kchhatbar0210@gems.sw.rim.net", "kchhatbar0216@gems.sw.rim.net", "kchhatbar0213@gems.sw.rim.net", "rudani0210@gems.sw.rim.net", "rudani0216@gems.sw.rim.net", "rudani0213@gems.sw.rim.net", "rpourwal0210@gems.sw.rim.net", "rpourwal0213@gems.sw.rim.net", "pppourwal0216@gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail1') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0316@gems.sw.rim.net", "kchhatbar0310@gems.sw.rim.net", "kchhatbar0316@gems.sw.rim.net", "kchhatbar0313@gems.sw.rim.net", "rudani0310@gems.sw.rim.net", "rudani0316@gems.sw.rim.net", "rudani0313@gems.sw.rim.net", "rpourwal0310@gems.sw.rim.net", "rpourwal0313@gems.sw.rim.net", "pppourwal0316@gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail2') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0416@gems.sw.rim.net", "kchhatbar0410@gems.sw.rim.net", "kchhatbar0416@gems.sw.rim.net", "kchhatbar0413@gems.sw.rim.net", "rudani0410@gems.sw.rim.net", "rudani0416@gems.sw.rim.net", "rudani0413@gems.sw.rim.net", "rpourwal0410@gems.sw.rim.net", "rpourwal0413@gems.sw.rim.net", "pppourwal0416@gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('GCUrl') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["gw-gc-01.gems.sw.rim.net", "gw-gc-02.gems.sw.rim.net", "cgw-gc-03.gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('primaryAccountdisplayName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["p purwal012016", "Krunal Chhatbar012010", "Krunal Chhatbar012013", "Krunal Chhatbar012016", "Rishit Udani01", "ramesh pourwal012010", "ramesh pourwal012013", "Premprakash Pourwal012016"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('secondaryAccountdisplayName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["p purwal022016", "Krunal Chhatbar032010", "Krunal Chhatbar032013", "Krunal Chhatbar032016", "Rishit Udani03", "Premprakash Pourwal032016"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('secondary1AccountdisplayName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["p purwal032016", "gw cland4", "Krunal Chhatbar032010", "Krunal Chhatbar032013", "Krunal Chhatbar032016", "rudani03", "ramesh pourwal032010", "ramesh pourwal032013", "premprakash pourwal032016", "Android Automation3", "Android Automation7"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('secondary2AccountdisplayName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["p purwal042016", "Krunal Chhatbar042010", "Krunal Chhatbar042013", "Krunal Chhatbar042016", "Rishit Udani04", "ramesh pourwal042010", "ramesh pourwal042013", "Premprakash Pourwal042016"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('primaryDevice') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["TA-1004","Pixel 2","Nexus 5X","Nexus 5X","TA-1004","Pixel XL","SM-G935F","SM-G935F","Nokia 6.1","SM-A600G","XT1663"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('primaryDeviceSerial') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["NB1GAD1772015460(TA-1004-GD094)","HT7AD1A01882(Pixel 2-GD093)","00f019e57db0a523(Nexus 5X-GD061)","00fb65f980f0a528(Nexus 5X-GD029)","NB1GAD1783102904(TA-1004-GD095)","HT7540206344(Pixel XL-GD074)","ce011711bd9148cf0c(SM-G935F-GD82)","ce011711d9546c700c(SM-G935F-GD0973)","PL2GAR4820504726(Nokia 6.1-GD098),"5200a161fe70159f(SM-A600 -GD099)","FEJVSGDUBM49NZRS(XT1663-GD077)"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('TestRunID') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["49646"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            booleanParam('systemShutDown', false)
            stringParam('projectBasedirectory', '${WORKSPACE}')
        }

        scm {
            perforceP4('4cc9c3ea-a1d6-421e-a870-94dfc6e6fbfb') {
                workspace {
                    manual('jenkinsWorkspace', '//depot/automation/BBQAAndroidAuto/... //jenkinsWorkspace/depot/automation/BBQAAndroidAuto/...')
                }
                configure { node ->
                    node / workspace / spec / clobber('true')
                }
            }
        }
        
        // Root pom.xml path
        rootPOM("depot/automation/BBQAAndroidAuto/pom.xml")

        // Set exec goals
        goals('clean compile install')

        // Allows Jenkins to schedule and execute multiple builds concurrently.
        concurrentBuild()

        // Manages how long to keep records of the builds.
        logRotator {
            // If specified, only up to this number of builds have their artifacts retained.
            artifactNumToKeep(50)
            // If specified, only up to this number of build records are kept.
            numToKeep(50)
        }

        // Block any upstream and downstream projects while building current project
        configure {
            def aNode = it
            def anotherNode = aNode / 'blockBuildWhenDownstreamBuilding'
            anotherNode.setValue('true')
            (it / 'blockBuildWhenUpstreamBuilding').setValue('true')
        }

        // Adds pre/post actions to the job.
        wrappers {
            colorizeOutput()
            timestamps()
        }

        // Allows to publish archive artifacts
        publishers {
            publishHtml {
                report('depot/automation/BBQAAndroidAuto/maven-output') {
                    reportName('Maven HTML Report')
                    reportFiles('emailable-report.html')
                    keepAll()
                    allowMissing()
                    alwaysLinkToLastBuild()
                }
                report('${WORKSPACE}') {
                    reportName('SeeTest HTML Report')
                    reportFiles('')
                    keepAll()
                    allowMissing()
                    alwaysLinkToLastBuild()
                }
            }
        }
    }
}