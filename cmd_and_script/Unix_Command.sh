$ ps    # Active process
$ ps -ef    # process id and other details
$ ps -ef | grep dimension   # filter process by application
$ kill <PID>  # Kill or stop existing running process with process ID
$ kill -9 <PID> ## force stop

$ java -jar dimension-api.jar   # Run the jar in a normal way
$ ./dimension-api.jar start

$ ls | grep dimen   # find all file/folder name with dimen
$ grep "ERROR" api.log    # all occurance of ERROR in api.log file
$ grep -r "ERROR" log   # all occurance of ERROR in log directory
$ head api.log    # first 10 line log from api.log file
$ tail api.log    # last 10 line log from api.log file
$ tail -n 20 api.log
$ tail -n 20 api.log app.log    # last 20 line log from combined file app and api
$ tail -n 20 -f api.log   # -f for live log
$ tail -n 20 api.log | grep "ERROR"

$ find -type f -name "api.log"    # find file with name api.log
$ find -type d -name "log"    # find directory with name log

$ cd process  # change directory
$ mkdir test    # create a directory with name test
$ rmdir test    # remove empty directory
$ rm api.log  # delete empty file
$ cp file1.txt file2.txt    # copy content from file1.txt to file2.txt
$ mv file.txt tmpFolder   # move file to tmpFolder
$ diff file1.txt file2.txt    # line by line difference between file1.txt vs file2.txt
