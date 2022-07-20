@echo off
set PHUB_DIR=src\test\resources\dataset\phub
set PHUB_REPO=git@gitlab.shopbase.dev:brodev/automation-print-dataset.git
echo "Clone image of phub"
IF EXIST %PHUB_DIR% (
	echo "Updating phub resources"
	git  -C %PHUB_DIR% pull origin master
) ELSE (
	echo "Cloning phub resource"
	git clone %PHUB_REPO% %PHUB_DIR% --depth=1
)