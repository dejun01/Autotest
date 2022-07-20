#### Requirements:
- Maven
- Java
#### Doc:
 `http://thucydides.info/docs/serenity/`
#### Get the code
#### Git:
`git clone git@gitlab.shopbase.dev:brodev/automation-bdd-shopbase.git`
`cd automation-bdd-shopbase.git`
#### Download PrintBase resources
If you want to run PrintBase testcase, you need to download all media files

- Mac/Linux: `bash setup_print.sh`
- Windows: `setup_print.bat`

#### Open a command window and run:
`mvn clean install -Dmaven.test.skip=true`
#### Run test on command line with tags
`mvn clean test verify -Dcucumber.options="--tags @image" -Dwebdriver.driver=chrome -Dwebdriver.remote.url=http://165.22.183.147:44440/wd/hub` 
https://johnfergusonsmart.com/running-serenity-bdd-tests-with-tags/
##### Run all test :
`mvn clean verify`
##### Check java process on window
`cmd >jps`
Kill process
`cmd >taskkill /pid %PID%`
#### Browser
- config file `serenity.properties`:
 `chrome.switches=--headless`
- Config run test on BrowserStack
serenity.properties
```
webdriver.driver = provided
webdriver.provided.type = mydriver
webdriver.provided.mydriver = beeketing.BrowserStackSerenityDriver
# add device
br.realMobile =true
br.browser=iPhone
br.device=iPhone XS
br.os_version=12.1
```
pom :
```
<webdriver.driver>chrome</webdriver.driver>
<webdriver.chrome.driver>chromedriver.exe</webdriver.chrome.driver>
```


####Customise browser size
```
serenity.browser.height = 1200
serenity.browser.width = 1200
```

##### config environment trong file serenity.properties
- environment=
#### Config các link nếu có thay đổi theo environment trong file serenity.conf
#### Edit file serenity_default.properties=>serenity.properties
VD:
````
environments{
 prod_sbase {
        webdriver.base.url = "https://accounts.shopbase.com"
        gapi.url = "https://api.shopbase.com"
        user.name= "shopbase@beeketing.net"
        user.pwd="123456"
        shop="au-checkout-flow-customer-info.onshopbase.com"
    }
    ....
}
````

#### How to run JMeter on cicd
1. Create new file where defines all jmx files will be ran, example at shopbase/TC_shopbase.json
2. Create new JOB with parameter named CONFIGURATION_FILE and default value is path to json file that you want to trigger
# Automation
# Automation
