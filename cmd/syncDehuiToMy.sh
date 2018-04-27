#!/usr/bin/env bash
#同步德惠代码，无提交情况下，并同步给我们仓库
git  fetch origin
git checkout dev
git rebase origin/dev
git push norigin dev
