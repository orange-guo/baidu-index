# baidu-index

A application that download baidu index as csv

## Usage

Steps:  
1. From github action list one action you selected and download [click here](https://github.com/orange-guo/baidu-index/actions)
1. Exports variables(Shell)

```shell
export BAIDU_USS=""
# Your baidu uss 
export KEYWORDS=""
# keywords you search E.g. export KEYWORDS="蔡徐坤, 鸡你太美"
export START_DATE=""
# start date in search range, E.g. export START_DATE="2021-01-01"
export END_DATE="index end date, such as 2021-01-07"
# end date in search range, E.g. export END_DATE="2021-01-07"
```

2. execute java
```shell
java -jar baidu-index-0.1.0-SNAPSHOT-standalone.jar
```
3. open output.csv that in current working directory

## License

Copyright © 2021 orange-kuo

This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0 which
is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary Licenses when the conditions for such
availability set forth in the Eclipse Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your option) any later version, with the GNU
Classpath Exception which is available at https://www.gnu.org/software/classpath/license.html.
