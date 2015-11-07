# java-demo
Blue green deployment demo for Java applications.

This example assumes that a continuous integration system is deploying WAR with a known name at [dist](https://github.com/manageacloud/java-demo/tree/master/dist) folder. You can learn more about this technology by reading the [quickstart guide](https://manageacloud.com/quickstart).

To make this deployment you need to build:
 - the [role](https://manageacloud.com/configuration/java_war_demo) that contains the server configuration
 - the [macfile](https://goo.gl/SouFP3) that contains the infrastructure blueprint

# Step 1
Configure `aws` and `mac` tools

    pip install awscli
    pip install mac
    aws configure
    mac login
    mac provider credential amazon <access key> <secret_access_key>

    
# Step 2
Deploy the infrastructure version 1.

    mac -s infrastructure macfile https://goo.gl/SouFP3 -p INF_VERSION=1 WAR_VERSION=1

The output displays the DNSName of the elastic load balancer. In a real word deployment, this would be the CNAME of the domain name where the application resides. You can access at this value at any time. Open it in a browser to see the results.

    mac resource get_stdout demo 1 build_lb_inf


# Step 3
Let's emulate a green blue deployment.

    mac -s infrastructure macfile https://goo.gl/SouFP3 -p INF_VERSION=2 WAR_VERSION=2
    
There are two infrastructures running in pararel an conecting to the same RDS.

    mac infrastructure list
    
You can access to the DNSName of the load balancer:

    mac resource get_stdout demo 2 build_lb_inf
    
# Step 4
Destroy the old infrastructure version 1

    mac infrastructure destroy demo 1
