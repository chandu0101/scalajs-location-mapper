#!/bin/sh



# get comment
comment="$1"

sbt fullOptJS

if [ "$comment" == "" ]; then
comment="push form CI"
echo "no comment specified to deploy, using default : $comment"
fi

projectName="scalajs-location-mapper"

ghPagesPath="/Users/chandrasekharkode/Desktop/Kode/Programming/scalaprojects/chandu0101.github.io"

projectPath=${ghPagesPath}/${projectName}

mkdir -p ${projectPath}/js

cp index.html ${projectPath}

cp  js/scalajs-location-mapper-opt.js ${projectPath}/js/

cp  js/scalajs-location-mapper-jsdeps.js ${projectPath}/js/

cp -r styles ${projectPath}/styles/

cp -r images ${projectPath}/images/

cd ${ghPagesPath}

git add ${projectName}

git commit -m "$comment"

git push