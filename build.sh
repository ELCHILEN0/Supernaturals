#! /bin/bash

clear
echo "Building and Packaging Project"

while getopts o:c option
do
        case "${option}"
        in
                o) OUTPUT=${OPTARG};;
                c) CLEAR=0;;
        esac
done

if [ -z "$OUTPUT" ]
  then
    echo "No file path specified.  Skipping copy."
  else
    echo "Copying packaged file…"
    cp target/*.jar $OUTPUT
fi

if [ $CLEAR ]
  then
    clear
fi
