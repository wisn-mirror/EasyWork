#!/usr/bin/env bash
#同步别人代码，无提交情况下，并同步给德惠
git  fetch norigin
git checkout dev
git rebase norigin/dev
git rebase norigin/master_kevin
#git merge --no-ff  norigin/master_kevin
git push norigin dev
git push origin dev