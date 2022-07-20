#!/bin/bash
PHUB_DIR="src/test/resources/dataset/phub"
PHUB_REPO="git@gitlab.shopbase.dev:brodev/automation-print-dataset.git"
echo "Clone image of phub"
if [ -d "$PHUB_DIR" ]; then
	echo "Updating phub resources"
	cd $PHUB_DIR
	git pull origin master
else
	echo "Cloning phub resource"
	git clone $PHUB_REPO $PHUB_DIR --depth=1
fi