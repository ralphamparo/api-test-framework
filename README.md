## Overview
The **`api-test-framework`** project holds sample tests for the [Restful-Booker Heroku app](https://restful-booker.herokuapp.com/apidoc/index.html).
The tests are by no means comprehensive in that the project simply showcases how a sample API test framework is structured.

This project should be considered half-baked in that you are expected to extend and add your own features onto the framework depending on your testing needs.

<br/>

##### Current Features:
* Reporting configured as a test listener
* Schema validation
* Rest-Assured tests using TestNG and Cucumber
* CI-friendly


## How to Use

On your machine, _**`cd`**_ into your desired directory then clone

```bash
$ git clone https://github.com/krismaglasang/api-test-framework.git
```

Then type in the ff. command to run the test

```bash
$ mvn test
```

Once the test has concluded, a report will be generated for you at the _**`/reports`**_ directory.

The same command will be used if you wish to hook your tests to a CI pipeline.

_Note that you'll need to have [Maven](https://maven.apache.org/) installed and added to your environment variables for the command to work. Also make sure that Maven imports all your dependencies._

Alternatively, you can simply just run the test class located in the **`/tests`** package. But this approach won't generate a report.

<br/>

More info about the code are written in the form of comments and descriptions where needed. So make sure to check them out there.

<br/>

And that's it.

**Happy Testing!**
