step 1: Have created the Account Service Microservice with repo, controller,service,kafka-consumer

step 2: createed the end points for addNewAccount, getAccountById,depositeValueToAccountAvailable ,	pdebitValueToAccountAvailable

step3: if we are crediting the account in that case kafka producer template will send a kafka notification message to kafka-consume with topic **diposite-event-topic**

ste4: the update(debeitted and creditted) operation is been synchronized .

step5: have put the kafka properties inside the application .yml 

step6: if we are debitting amount from the account there are 2 case
    case1: if the available balance will be grater then requestedAmount then it will send the message notification to kafka- consumer with topic **debited-event-topic**
    case2: if the available balance will be lesser then the requested amount then it will send the message notification to kafka- consumer with topic **amount-not-sufficient-topic**

**NOTE**: the zip file you attached somehow is not opening in my laptop due to some firewall issue  appologies for that 
there was some time sort to right test cases but i will put them here if you want ,but i checked through postman services were up and running.
