cd ~/code/FintechSearch
echo '#########################begin to pull the latest code'
if [[ $# -eq 0 ]];then
  #echo "###### git checkout master"
  #git checkout master
  git pull
fi
if [[ $# -gt 0 ]];then
  echo "##### git checkout $1"
  git checkout $1
  git pull
fi

echo '#########################end to pull the latest code'

echo '#########################do mvn clean install'
mvn -e -Dcashbus.env=PROD clean install

echo 'copying into tmp'
rm -Rf ~/tmp/search

cd ~/code/FintechSearch/target/
mv Search-SNAPSHOT search
cp -r ./search ~/tmp