Hello, 
It works only with 1-3 words.  If empty that it show all fruits.

I think it is possible to find solution sort of for more words:
Sort of this request should take list of our tags
SELECT * FROM hits where (tags  LIKE  '%' || :tag1 || '%' or tags LIKE '%' || :tag2 || '%')