git filter-branch -f --env-filter \
"GIT_AUTHOR_NAME='natashaval'; \
GIT_AUTHOR_EMAIL='natasha.valentina1998@gmail.com'; \
GIT_COMMITTER_NAME='natashaval'; \
GIT_COMMITTER_EMAIL='natasha.valentina1998@gmail.com';" \
HEAD;
git push --force origin main

