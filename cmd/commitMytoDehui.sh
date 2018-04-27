#!/usr/bin/env bash
#无冲突情况下，合并别人代码
git  fetch norigin
git checkout dev
git stash
git rebase norigin/dev
git stash pop
git commit -m "empty";
git checkout master_kevin
git rebase norigin/master_kevin
git checkout dev
git merge --no-ff  norigin/master_kevin
git push norigin dev
git push origin dev