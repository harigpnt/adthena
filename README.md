# adthena - Retail scenario - test project
This projects demonstrates a simple scala for a retail requirement as detailed below:
A shop has 4 items as listed below:
  1. Soup - 65p per tin
  2. Bread - 80p per loaf
  3. Milk - £1.30 per bottle
  4. Apples £1.00 per bag

There are currently 2 offers/discounts that are applicable to customers as detailed below:
1. A 10% discount of apples
2. Buy 2 soups and get 50% off on bread

Customers/users should be given the option to add items along with respective quantities to their basket and checkout

Technical instructions:
This code is built using scala and SBT. Please see build.sbt file for versions

Easiest way of executing the project:
Download the file "/adthena/out/adthena.jar" and execute it in your command prompt with the command below:
java -jar "/<local-folder>/adthena.jar" Retail
(Be wary of the slash, while MacOS and Unix uses backward slash, Windows uses a forward slash for folder paths)

Hard way of executing the project: (Do not worry there is an
If you do not have git, please install git from the site below:
https://git-scm.com/downloads
Go to the command prompt and type the below:
git clone "<repository-name>"
Once you have pulled the project, you can go to your command prommpt and run the sbt commands:
sbt compile
sbt run (for executing the application)
sbt package (For building a jar using sbt)

If you do not have sbt installed, you can download it from the link below:
https://www.scala-sbt.org/download.html

You could also clone this project using your IDEs such as Intellij IDEA or Eclipse, etc.
You should go to the VCS menu(On Intellij IDEA) and create a new git oull request, specify the repository name, branch name.

Should you have any queries, please feel free to add comments on this repository. Also report any issues in the issues tab should you come across one.



