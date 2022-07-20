#!/bin/bash
bash setup_print.sh
rm -rf runner
git clone git@gitlab.shopbase.dev:brodev/cucumber-runner.git runner --depth=1
php runner/run_cucumber_test.php