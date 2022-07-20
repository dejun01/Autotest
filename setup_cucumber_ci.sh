#!/usr/bin/env sh

envPath="/mnt/data-0/automation-bdd-shopbase/staging"
envBranch="master"

if [ $2 = 'production' ]
then
    envPath="/mnt/data-0/automation-bdd-shopbase/production"
    envBranch="production"
fi

if [ -d "$envPath" ]
then
    git pull origin $envBranch
else
    git clone -b "$envBranch" git@gitlab.shopbase.dev:brodev/automation-bdd-shopbase.git "$envPath" --depth=1
fi

cd $envPath
rm -rf runner
git clone git@gitlab.shopbase.dev:brodev/cucumber-runner.git runner --depth=1
php runner/run_cucumber_test_ci.php $1 $2 $3