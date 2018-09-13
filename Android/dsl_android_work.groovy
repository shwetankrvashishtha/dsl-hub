/**
 * @author shwetankvashishtha
 *
 */

 def signature = 'new groovy.json.JsonSlurperClassic'
 org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get().approveSignature(signature)
 
 def branchesParam = binding.variables.get('BRANCHES')
 def branches = branchesParam ? branchesParam.split(' ') : ['BB_Work']

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
                    script('["Android_Work_Sanity", "Android_Work_Validation", "Android_Work_VAT", "Android_Work_Smoke"]')
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
            activeChoiceParam('ProvisionAPK') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["trunk", "dev", "master", "patch"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            stringParam('BuildNumber', 'latest')
            stringParam('BuildNumberWithVersion', '2.13.0.1055 (for latest build 2.13.0. )')
            activeChoiceParam('TNApkType') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["merged", "release"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            stringParam('TNBuildNumber', '229')
            activeChoiceParam('Server') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["cloud", "onPrem", "uem", "pu"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            booleanParam('CloudUserStatus', false)
            booleanParam('Core2Status', false)
            activeChoiceReactiveParam('OwaUrl') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (Server.equals("cloud")) {
                            return ['outlook.office365.com/ews/Exchange.asmx'];
                            } else if (Server.equals("onPrem")) {
                                return ['gs-ex16.gems.sw.rim.net/ews/Exchange.asmx'];
                                } else {
                                    return ['Unknown state']
                                }
                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('Server')
            }
            activeChoiceReactiveParam('ExternalUsersOwaUrl') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (Server.equals("cloud")) {
                            return ['autodiscover.core2.sqm.testnet.rim.net/ews/Exchange.asmx'];
                            } else if (Server.equals("onPrem")) {
                                return ['autodiscover.core2.sqm.testnet.rim.net/ews/Exchange.asmx'];
                                } else {
                                    return ['Unknown state']
                                }
                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('Server')
            }
            activeChoiceReactiveParam('AuthorizedUsersOwaUrl') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (Server.equals("cloud")) {
                            return ['gs-ex16.gems.sw.rim.net/ews/Exchange.asmx'];
                            } else if (Server.equals("onPrem")) {
                                return ['outlook.office365.com/ews/Exchange.asmx'];
                                } else {
                                    return ['Unknown state']
                                }
                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('Server')
            }
            activeChoiceReactiveParam('ExchangeServer') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (Server.equals("cloud")) {
                            return ['outlook.office365.com'];
                            } else if (Server.equals("onPrem")) {
                                return ['gs-ex10.gems.sw.rim.net','gs-ex13.gems.sw.rim.net','gs-ex16.gems.sw.rim.net'];
                                } else {
                                    return ['Unknown state']
                                }
                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('Server')
            }
            activeChoiceReactiveParam('domain') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (Server.equals("cloud")) {
                            return ['goodtech.onmicrosoft.com'];
                            } else if (Server.equals("onPrem")) {
                                return ['gems'];
                                } else {
                                    return ['Unknown state']
                                }
                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('Server')
            }
            activeChoiceParam('PrimaryEmail') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0116@gems.sw.rim.net", "gwcland6@goodtech.onmicrosoft.com", "kchhatbar0110@gems.sw.rim.net", "kchhatbar0116@gems.sw.rim.net", "kchhatbar0113@gems.sw.rim.net", "rudani0110@gems.sw.rim.net", "rudani0116@gems.sw.rim.net", "rudani0113@gems.sw.rim.net", "rpourwal0110@gems.sw.rim.net", "rpourwal0113@gems.sw.rim.net", "pppourwal0116@gems.sw.rim.net", "androidautomation1@core2.sqm.testnet.rim.net", "androidautomation5@core2.sqm.testnet.rim.net", "androidautomation11@core2.sqm.testnet.rim.net", "androidautomation15@core2.sqm.testnet.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0216@gems.sw.rim.net", "gwcland7@goodtech.onmicrosoft.com", "kchhatbar0210@gems.sw.rim.net", "kchhatbar0216@gems.sw.rim.net", "kchhatbar0213@gems.sw.rim.net", "rudani0210@gems.sw.rim.net", "rudani0216@gems.sw.rim.net", "rudani0213@gems.sw.rim.net", "rpourwal0210@gems.sw.rim.net", "rpourwal0213@gems.sw.rim.net", "pppourwal0216@gems.sw.rim.net", "androidautomation2@core2.sqm.testnet.rim.net", "androidautomation6@core2.sqm.testnet.rim.net", "androidautomation12@core2.sqm.testnet.rim.net", "androidautomation16@core2.sqm.testnet.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail1') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0316@gems.sw.rim.net", "gwcland4@goodtech.onmicrosoft.com", "kchhatbar0310@gems.sw.rim.net", "kchhatbar0316@gems.sw.rim.net", "kchhatbar0313@gems.sw.rim.net", "rudani0310@gems.sw.rim.net", "rudani0316@gems.sw.rim.net", "rudani0313@gems.sw.rim.net", "rpourwal0310@gems.sw.rim.net", "rpourwal0313@gems.sw.rim.net", "pppourwal0316@gems.sw.rim.net", "androidautomation3@core2.sqm.testnet.rim.net", "androidautomation7@core2.sqm.testnet.rim.net", "androidautomation13@core2.sqm.testnet.rim.net", "androidautomation17@core2.sqm.testnet.rim.net"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('SecondaryEmail2') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["ppurwalauto0416@gems.sw.rim.net", "gwcland5@goodtech.onmicrosoft.com", "kchhatbar0410@gems.sw.rim.net", "kchhatbar0416@gems.sw.rim.net", "kchhatbar0413@gems.sw.rim.net", "rudani0410@gems.sw.rim.net", "rudani0416@gems.sw.rim.net", "rudani0413@gems.sw.rim.net", "rpourwal0410@gems.sw.rim.net", "rpourwal0413@gems.sw.rim.net", "pppourwal0416@gems.sw.rim.net", "androidautomation4@core2.sqm.testnet.rim.net", "androidautomation8@core2.sqm.testnet.rim.net", "androidautomation14@core2.sqm.testnet.rim.net", "androidautomation18@core2.sqm.testnet.rim.net"]')
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
                    script('["p purwal012016", "gw cland6", "Krunal Chhatbar012010", "Krunal Chhatbar012013", "Krunal Chhatbar012016", "r udani01", "ramesh pourwal012010", "ramesh pourwal012013", "premprakash pourwal012016", "Android Automation1", "Android Automation5"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('secondaryAccountdisplayName') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["p purwal022016", "gw cland7", "Krunal Chhatbar012010", "Krunal Chhatbar022013", "Krunal Chhatbar022016", "rudani02", "ramesh pourwal022010", "ramesh pourwal022013", "premprakash pourwal022016", "Android Automation2", "Android Automation6"]')
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
                    script('["p purwal042016", "gw cland5", "Krunal Chhatbar042010", "Krunal Chhatbar042013", "Krunal Chhatbar042016", "rudani04", "ramesh pourwal042010", "ramesh pourwal042013", "premprakash pourwal042016", "Android Automation4", "Android Automation8"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('primaryDevice') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["GD094","GD093","GD061","GD029","GD095","GD074","GD082","GD073","GD098","GD099","GD077","GD0100","GD063","GD088","GD060","GD049","GD096","GD066"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceParam('TestRunId') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["49646"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceReactiveParam('primaryDeviceSerial') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (primaryDevice.equals("GD094")) {
                            return ['NB1GAD1772015460'];
                            } else if (primaryDevice.equals("GD093")) {
                                return ['HT7AD1A01882'];
                                } else if (primaryDevice.equals("GD061")) {
                                    return ['00f019e57db0a523'];
                                    } else if (primaryDevice.equals("GD029")) {
                                        return ['00fb65f980f0a528'];
                                        } else if (primaryDevice.equals("GD095")) {
                                            return ['NB1GAD1783102904'];
                                            } else if (primaryDevice.equals("GD074")) {
                                                return ['HT7540206344'];
                                                } else if (primaryDevice.equals("GD082")) {
                                                    return ['ce011711bd9148cf0c'];
                                                    } else if (primaryDevice.equals("GD073")) {
                                                        return ['ce011711d9546c700c'];
                                                        } else if (primaryDevice.equals("GD098")) {
                                                            return ['PL2GAR4820504726']; 
                                                            } else if (primaryDevice.equals("GD099")) {
                                                                return ['5200a161fe70159f'];
                                                                } else if (primaryDevice.equals("GD077")) {
                                                                    return ['FEJVSGDUBM49NZRS'];
                                                                    } else if (primaryDevice.equals("GD0100")) {
                                                                        return ['B2NGAA8842404819'];
                                                                        } else if (primaryDevice.equals("GD063")) {
                                                                            return ['HT6960203976'];
                                                                            } else if (primaryDevice.equals("GD088")) {
                                                                                return ['12FA7AE1A02837'];
                                                                                } else if (primaryDevice.equals("GD060")) {
                                                                                    return ['08b433b00de77bc8'];
                                                                                    } else if (primaryDevice.equals("GD049")) {
                                                                                        return ['019ac524f0a69398'];
                                                                                        } else if (primaryDevice.equals("GD096")) {
                                                                                            return ['ce10171ac02b6b19057e'];
                                                                                            } else if (primaryDevice.equals("GD066")) {
                                                                                                return ['ZX1G229T7C'];
                                                                                                } else {
                                                                                                    return ['Unknown state']
                                                                                                }
                                                                                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('primaryDevice')
            }
            activeChoiceParam('secondaryDevice') {
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('["GD094","GD093","GD061","GD029","GD095","GD074","GD082","GD073","GD098","GD099","GD077","GD0100","GD063","GD088","GD060","GD049","GD096","GD066"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceReactiveParam('secondaryDeviceSerial') {
                choiceType('SINGLE_SELECT')
                groovyScript {
                    script('''
                        if (secondaryDevice.equals("GD094")) {
                            return ['NB1GAD1772015460'];
                            } else if (secondaryDevice.equals("GD093")) {
                                return ['HT7AD1A01882'];
                                } else if (secondaryDevice.equals("GD061")) {
                                    return ['00f019e57db0a523'];
                                    } else if (secondaryDevice.equals("GD029")) {
                                        return ['00fb65f980f0a528'];
                                        } else if (secondaryDevice.equals("GD095")) {
                                            return ['NB1GAD1783102904'];
                                            } else if (secondaryDevice.equals("GD074")) {
                                                return ['HT7540206344'];
                                                } else if (secondaryDevice.equals("GD082")) {
                                                    return ['ce011711bd9148cf0c'];
                                                    } else if (secondaryDevice.equals("GD073")) {
                                                        return ['ce011711d9546c700c'];
                                                        } else if (secondaryDevice.equals("GD098")) {
                                                            return ['PL2GAR4820504726']; 
                                                            } else if (secondaryDevice.equals("GD099")) {
                                                                return ['5200a161fe70159f'];
                                                                } else if (secondaryDevice.equals("GD077")) {
                                                                    return ['FEJVSGDUBM49NZRS'];
                                                                    } else if (secondaryDevice.equals("GD0100")) {
                                                                        return ['B2NGAA8842404819'];
                                                                        } else if (secondaryDevice.equals("GD063")) {
                                                                            return ['HT6960203976'];
                                                                            } else if (secondaryDevice.equals("GD088")) {
                                                                                return ['12FA7AE1A02837'];
                                                                                } else if (secondaryDevice.equals("GD060")) {
                                                                                    return ['08b433b00de77bc8'];
                                                                                    } else if (secondaryDevice.equals("GD049")) {
                                                                                        return ['019ac524f0a69398'];
                                                                                        } else if (secondaryDevice.equals("GD096")) {
                                                                                            return ['ce10171ac02b6b19057e'];
                                                                                            } else if (secondaryDevice.equals("GD066")) {
                                                                                                return ['ZX1G229T7C'];
                                                                                                } else {
                                                                                                    return ['Unknown state']
                                                                                                }
                                                                                                ''')
                    fallbackScript('return ["ERROR"]')
                }
                referencedParameter('secondaryDevice')
            }
            booleanParam('systemShutDown', false)
            stringParam('projectBasedirectory', '${WORKSPACE}')
        }

        scm {
            perforceP4('4cc9c3ea-a1d6-421e-a870-94dfc6e6fbfb') {
                workspace {
                    manual('jenkinsWorkspaceNew', '//depot/automation/BBQAAndroidAuto/... //jenkinsWorkspaceNew/depot/automation/BBQAAndroidAuto/...')
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
                    reportFiles('*.htm')
                    keepAll()
                    allowMissing()
                    alwaysLinkToLastBuild()
                }
                report('depot/automation/BBQAAndroidAuto/seetest-reports/report') {
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