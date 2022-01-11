# restester
Automation framework for rest api testing via json files.

The idea here is to make it easy to automate REST tests without any coding, only with one (or more) json files.

## Install
* gine clone git@github.com:NilVentosa/restester.git
* mvn clean compile assembly:single

## To run
java -jar restester-jar-with-dependencies.jar testPlanFile.json

A .xml report will be produced after execution in the same folder where the tool is. The results are also printed to the screen.

## Test plan file example
* Endpoint and url can be specified at request, test suite and test plan level. The preference is in that order.
* suites, tests and parameters accept multiple instances.
* If a mandatory field is not there it will tell you somehow
```json
{
  "name": "Test plan one",
  "url": "http://testplan.com",
  "suites": [
    {
      "name": "Suite one",
      "description": "Description of the first suite",
      "url": "https://reqres.in",
      "tests": [
        {
          "name": "Test one",
          "description": "This is the first test",
          "request": {
            "endpoint": "/api/users",
            "parameters": [
              {"key": "page", "value": "2"},
              {"key": "per_page", "value": "3"}
            ]
          },
          "response": {
            "code": 200
          }
        }
      ]
    }
  ]
}
```
## Jenkins integration
After execution a .xml file will be produced with the results. The file can be fed to the junit plugin in jenkins.

## Note
This is of course a work in progress. Currently it only asserts for the response code. I plan to add more stuff to it. If you want to tell me something about this: ventosa@gmail.com

## Things I want to add
- [ ] Yaml and xml support for the plan file
- [ ] Authentication
- [ ] DB steps for before and after (plan, suite, test, request...)
- [ ] More assertions
- [ ] Specify headers and method
- [ ] ...
