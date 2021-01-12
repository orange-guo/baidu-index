#!/bin/bash
export BAIDU_USS=""
# Your baidu uss
export KEYWORDS=""
# keywords you search E.g. export KEYWORDS="蔡徐坤, 鸡你太美"
export START_DATE=""
# start date in search range, E.g. export START_DATE="2021-01-01"
export END_DATE=""
# end date in search range, E.g. export END_DATE="2021-01-07"
java -jar ./baidu-index-standalone-0.0.1.jar