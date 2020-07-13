#!/usr/bin/env bash

rm -rf ./result* ./parents.txt ./pupils.txt

FILENAME=fortests.sql
LINE_COUNT=$(wc -l "$FILENAME" | awk '{print $1}')

#Dobavlenije STR_TO_DATE k datam

input="$FILENAME"
while IFS= read -r line
do
  echo "$line" | awk '$19="STR_TO_DATE("$19" `%m/%d/%Y`),"' >> result1.txt
done < "$input"


#zamena simvolov kavichek - ` na '
sed "s/\`/\'/g" < result1.txt > result2.txt

#Ustanovka familyId
input="result2.txt"
IS_FAMILY=false
FAMILY_ID='';

PUPIL_INPUT="pupils_data.sql"
PUPIL_COUNT=$(wc -l "$PUPIL_INPUT" | awk '{print $1}')
PUPIL_NO=0;

for (( i = 1 ; i <= LINE_COUNT ; i++ )); do
  FILE_LINE=$(sed -n "$i"p "$input");
  FAMILY_MEMBER_COUNT=$(echo $((1 + RANDOM % 3)));
  FAMILY_ID=$(echo "$FILE_LINE" | awk '{print $NF}');
  echo "$FILE_LINE" >> parents.txt ;

  #pupils sub processing
    PUPIL_NO=$((PUPIL_NO+1));
    PUPIL_FILE_LINE=$(sed -n "$PUPIL_NO"p "$PUPIL_INPUT") ;
    echo "$(echo "$PUPIL_FILE_LINE" | awk 'NF{NF-=2};1')$FAMILY_ID" >> pupils.txt  ;

  for (( n = 1 ; n < FAMILY_MEMBER_COUNT ; n++ )); do
    i=$((i+1));
    FILE_LINE=$(sed -n "$i"p "$input") ;
    echo "$(echo "$FILE_LINE" | awk 'NF{NF-=1};1')$FAMILY_ID" >> parents.txt  ;
  done
done


#while IFS= read -r line
#do
#    if [ "$IS_FAMILY" = false ] ; then
#        FAMILY_MEMBER_COUNT=$(echo $((1 + RANDOM % 3)));
#    fi
#
#    for ((i = 1 ; i <= $FAMILY_MEMBER_COUNT ; i++)); do
#      echo "Counter: $i"
#    done
#
##  echo "$line" | awk '$19="STR_TO_DATE("$19" `%m/%d/%Y`),"' >> result2.txt
#  echo "$line" | awk '{print $NF}' >> result2.txt
#done < "$input"