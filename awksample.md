awk related
ls -l | awk 'BEGIN {size=0;count=0;max=0} {size=size+$5;count=count+1;if($5>max){max=$5}} END{print "Total size is", size,"Total count is,"count,"Average size is",size/count,"Max is",max}'