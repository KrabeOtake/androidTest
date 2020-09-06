# Test assignment

We are looking for Automation Engineers that have the mindset "only the sky is the limit" and "automation doesn't stop at testing, it's just a beginning!" ;)

The purpose of this test assignment is to assess the applicant's automation skills, allowing him/her to show the best they can do and how fast they can learn.
It is an open assignment. There is no the right answer and there is no end goal other than proving yourself. Surprise us!

Make sure that you give detailed comments or descriptions of your tests.
When the assignment is complete, please push your solution to Github(Gitlab) and send us the link to hr.grid.adi@nl.abnamro.com.
If you have any questions, please contact Lotte Grundel at <lotte.grundel@nl.abnamro.com>.

Good luck.

PS. We don't expect you to spend weeks (and sleepless nights) on doing it. Lets see how far you can get in 6-10 hours. We want to see how you approach and solve problems.

PSPS. Please use mobile native tools. (Tests written on Java are accepted too)


# SUMMARY

## Test cases
There are 5 test cases that are stored on qTest Manager. 
- Check if "Hello world!" text is displayed
- Tap the float action button
- Check if toolbar title is displayed
- Tap the toolbar menu 
- Tap the toolbar menu "Settings" item

Each test case has defined steps and expected result description. Each test case is linked to its correspondent espresso autotest. The result of each espresso autotest is uploaded to the correspondent test case's execution table.
![Testcase screenshot](https://i.ibb.co/8K0h55j/Screenshot-2020-09-06-at-01-16-48.png)

## Espresso
Since it's a hello world. app, there isn't much to test. Espresso tests are more to show the integration with Jenkins and qTest.
There are 5 autotests located in `ExampleInstrumentedTest` class (one for each test case).
There is one custom view action - `waitView`, which waits for a view to appear on the screen =)

## Jenkins
Launched and connected a local Jenkins pipeline. It's set up to trigger a build after each push. The build steps and stages are described in Jenkinsfile.
There are 3 stages defined in Jenkinsfile:
- Build app
- Build test app
- Instrumented test

Inside the last stage we launch the emulator, wait for it to boot and launching the espresso test run.
![Jenkins screenshot](https://i.ibb.co/W3nJZfz/Screenshot-2020-09-06-at-01-16-19.png)

As a post-build action we generate a jUnit report and upload it to qTest:
![Jenkins screenshot](https://i.ibb.co/RcyJPPY/Screenshot-2020-09-05-at-00-13-12.png)

## qTest
The jUnit results of instrumented tests are uploaded to the qTest Manager [here](https://androidtest.qtestnet.com/) inside a post-build action:
![qTest screenshot](https://i.ibb.co/9w7x8T8/Screenshot-2020-09-06-at-01-38-06.png)

## Slack notification
As a last step of psot-build action, we send a slack message to our #jenkins-test channel to notify developers about the build status:
 
![Slack screenshot](https://i.ibb.co/MRKz3Yh/Screenshot-2020-09-06-at-02-33-21.png)
