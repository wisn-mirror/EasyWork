#!/usr/bin/env bash
git  fetch norigin
git checkout dev
git rebase norigin/dev
git rebase norigin/master_kevin
git push norigin dev
git push origin dev