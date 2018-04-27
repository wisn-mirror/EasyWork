#!/usr/bin/env bash
git  fetch origin
git checkout dev
git rebase origin/dev
git push norigin dev
