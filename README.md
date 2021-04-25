# Cloud_Deployment_PCF_Spring_Boot

Cloud URL: https://productservice.cfapps.io/swagger-ui.html

Create a account in https://login.run.pivotal.io/login


How to deploy application on Pivotal Cloud Foundry
Download Cloud Foundry CLI using below URL:
Link: https://github.com/cloudfoundry/cli#installers-and-compressed-binaries


In your application add a file manifest.yml with below configuration
=====================================================================
---
applications:
- name: ProductService
  instances: 1
  memory: 1G
  path: target/ProductService-0.0.1-SNAPSHOT.jar
  domain: cfapps.io

===================================================  

Use command > cf login  and login to application  if it will ask for APIEndpoint use http://api.run.pivotal.io

Go into your application location and use command: cf push
This command will automatically deploy and start your application on PCF, you can see the details by login to PCF console  
https://console.run.pivotal.io/

Go to routes to check the app URL:  https://productservice.cfapps.io/swagger-ui.html#/

If you want to remove your app from cloud use command  : cf delete ProductService



Add your PCF credentials in to Jenkins while creating Pipeline Job
