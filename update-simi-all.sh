cd /data/src/meijia-parent/
git reset --hard HEAD
git pull
mvn clean package
/data/tomcat/bin/shutdown.sh
sleep 1
rm -rf /data/tomcat/webapps/simi
\cp -rf /data/src/meijia-parent/simi-app/target/simi.war /data/tomcat/webapps/

rm -rf /data/tomcat/webapps/simi-oa
\cp -rf /data/src/meijia-parent/simi-oa/target/simi-oa.war /data/tomcat/webapps/

rm -rf /data/tomcat/webapps/xcloud
\cp -rf /data/src/meijia-parent/xcloud/target/xcloud.war /data/tomcat/webapps/

rm -rf /data/tomcat/webapps/resume
\cp -rf /data/src/meijia-parent/resume-app/target/resume.war /data/tomcat/webapps/

/data/tomcat/bin/startup.sh

