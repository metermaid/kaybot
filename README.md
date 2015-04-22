# Kaybot
A robot to tell you that you are ok by Keegs and Rita

## Build & Run ##
Make sure you have scala, postgreSQL & SBT installed.

Install scalatra: http://www.scalatra.org/getting-started/installation.html

Make a Twilio account and ML app and add the credentials as an environment variable, 'TWILIO', with the following format: `SID:TOKEN:APPSID`

```sh
$ cd Kaybot
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
