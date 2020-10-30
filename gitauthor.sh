git filter-branch -f --commit-filter \
"if [$GIT_COMMITTER_NAME='natashasantoso'];\
then\
  GIT_AUTHOR_NAME='natashaval';\
  GIT_AUTHOR_EMAIL='natasha.valentina1998@gmail.com';\
  GIT_COMMITTER_NAME='natashaval';\
  GIT_COMMITTER_EMAIL='natasha.valentina1998@gmail.com';\
  git commit-tree '$@';\
else\
  git commit-tree '$@';\
fi" HEAD;

# https://stackoverflow.com/questions/4493936/could-i-change-my-name-and-surname-in-all-previous-commits
