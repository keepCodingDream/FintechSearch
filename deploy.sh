/home/core_staff/env/tomcat8/bin/shutdown.sh
echo '######################shutdown tomcat'
sleep 5s
unset pid;
eval $(ps -ef | grep 'tomcat'|grep -v grep |grep -v tail | awk '{printf("pid=%s;", $2)}');

if [ "$pid" != "" ]; then
  echo "#########################start to kill tomcat process, pid=$pid"
  kill -9 $pid;
fi

echo '#########################sleep 5 seconds'
sleep 5s

cd /home/core_staff/env/tomcat8/webapps
rm -fr ROOT
cp -r /home/core_staff/tmp/search .
mv search ROOT

echo '#########################start tomcat'
/home/core_staff/env/tomcat8/bin/startup.sh