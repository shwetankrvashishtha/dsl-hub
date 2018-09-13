/**
 * @author shwetankvashishtha
 *
 */

 def signature = 'new groovy.json.JsonSlurperClassic'
 org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get().approveSignature(signature)
 
 def branchesParam = binding.variables.get('BRANCHES')
 def branches = branchesParam ? branchesParam.split(' ') : ['BB_Notes']

 def constants = [

    // generic params
    project: 'BB_Notes_Auto_iOS',
    component: 'SeeTest',
    branches: branches,
    name: 'iOS',
    
    ]

    def friendlyProject = constants.project.capitalize()
    def friendlyComponent = constants.component.capitalize()
    folder(constants.name) {
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
            activeChoiceParam('TestSuite') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["Runs/iPhone_Notes_Sanity", "Runs/iPhone_Tasks_Sanity", "Runs/iPhone_Notes_KPI", "Runs/iPhone_Tasks_KPI"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            stringParam('BuildNumber', '245 (0 for latest build)')
            stringParam('BuildVersion', '2.14.0.')
            activeChoiceParam('LabName') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["cloudTasksAndNotes", "onPremTasksAndNotes"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('PrimaryDevice') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["GD018", "GD019", "GD022", "GD052", "GD053", "GD054", "GD055", "GD056", "GD057", "GD058", "GD070", "GD085", "GD087", "GD090", "GD091"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('PrimaryEmail') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["niravpatel0110@gems.sw.rim.net", "niravpatel0113@gems.sw.rim.net", "niravpatel0116@gems.sw.rim.net", "Ashutosh0110@gems.sw.rim.net", "Ashutosh0113@gems.sw.rim.net", "Ashutosh0116@gems.sw.rim.net", "iizdebska@goodtech.onmicrosoft.com", "nimehtaauto0116@gems.sw.rim.net", "glimbachiyaauto0116@gems.sw.rim.net", "cpaliwalauto0116@gems.sw.rim.net", "kvasavaauto0116@gems.sw.rim.net", "bpandyaauto0116@gems.sw.rim.net", "niravpatelauto0110@gems.sw.rim.net", "niravpatelauto0116@gems.sw.rim.net", "pshrimaliauto0116@gems.sw.rim.net", "nirdaveauto0116@gems.sw.rim.net", "niymehtaauto0116@gems.sw.rim.net", "rkachhadiyaauto0116@gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["niravpatel0210@gems.sw.rim.net", "niravpatel0213@gems.sw.rim.net", "niravpatel0216@gems.sw.rim.net", "Ashutosh0210@gems.sw.rim.net", "Ashutosh0213@gems.sw.rim.net", "Ashutosh0216@gems.sw.rim.net", "njantakat@goodtech.onmicrosoft.com", "nimehtaauto0216@gems.sw.rim.net", "glimbachiyaauto0216@gems.sw.rim.net", "cpaliwalauto0216@gems.sw.rim.net", "kvasavaauto0216@gems.sw.rim.net", "bpandyaauto0216@gems.sw.rim.net", "niravpatelauto0210@gems.sw.rim.net", "niravpatelauto0216@gems.sw.rim.net", "pshrimaliauto0216@gems.sw.rim.net", "nirdaveauto0216@gems.sw.rim.net", "niymehtaauto0216@gems.sw.rim.net", "rkachhadiyaauto0216@gems.sw.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('PrimaryFullName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["Nirav_Patel01", "Nirav_Patel02", "Nirav_Patel03", "Nirav_Patel04", "Ashutosh_Kumar0110", "Ashutosh_Kumar0113", "Ashutosh_Kumar0116", "Iga_Izdebska", "ni_mehtaauto0116", "c_paliwalauto0116", "g_limbachiyaauto0116", "k_vasavaauto0116", "b_pandyaauto0116", "nirav_patelauto0110", "r_kachhadiyaauto0116", "p_shrimaliauto0116", "niy_mehtaauto0116", "nir_daveauto0116"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryFullName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["Nirav_Patel01", "Nirav_Patel02", "Nirav_Patel03", "Nirav_Patel04", "Ashutosh_Kumar0210", "Ashutosh_Kumar0213", "Ashutosh_Kumar0216", "N_Jantakat", "ni_mehtaauto0216", "c_paliwalauto0216", "g_limbachiyaauto0216", "k_vasavaauto0216", "b_pandyaauto0216", "nirav_patelauto0210", "r_kachhadiyaauto0216", "p_shrimaliauto0216", "niy_mehtaauto0216", "nir_daveauto0216"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('Release') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ios-g3-tasks-develop", "ios-g3-tasks-release"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('Server') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["gs-ex10.gems.sw.rim.net", "gs-ex13.gems.sw.rim.net", "gs-ex16.gems.sw.rim.net", "outlook.office365.com"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            nonStoredPasswordParam("EnterprisePassword", "")
            activeChoiceParam('Host') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["https://gw-gc-01.gems.sw.rim.net", "https://gw-gc-02.gems.sw.rim.net", "https://gw-gc-03.gems.sw.rim.net", "https://gdcloudgc.good.com/"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('DeviceModelPrimary') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["iPhone 5", "iPhone 6s", "iPhone 6+", "iPhone 6s+", "iPhone 7", "iPhone 7+", "iPhone 8", "iPhone 8+", "iPhone X"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('DeviceVersionPrimary') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["10.3.3", "11.1.2", "11.2.1", "11.2.5", "11.2.6", "11.3", "11.3.1", "11.4", "12.0"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            stringParam('TestRunID', '')
            booleanParam('systemShutDown', false)
        }

        scm {
            perforceP4('ae9da673-ec5d-4f7e-a337-1e0a1a476e8e') {
                workspace {
                    manual('JenkinsJobNotes', '//depot/automation/BBQAiPhoneAuto/... //JenkinsJobNotes/depot/automation/BBQAiPhoneAuto/...')
                }
                configure { node ->
                    node / workspace / spec / clobber('true')
                }
            }
        }
        
        // Root pom.xml path
        rootPOM("depot/automation/BBQAiPhoneAuto/pom.xml")

        // Set maven option
        mavenOpts('-DlabName=${LabName} -DtestSuite=${TestSuite}')

        // Set exec goals
        goals('clean compile test')

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
            preBuildCleanup()
            colorizeOutput()
            timestamps()
        }

        // Allows to publish archive artifacts
        publishers {
            publishHtml {
                report('depot/automation/BBQAiPhoneAuto/target/surefire-reports') {
                    reportName('Maven HTML Report')
                    reportFiles('*.htm')
                    keepAll()
                    allowMissing()
                    alwaysLinkToLastBuild()
                }
            }
        }
    }
}