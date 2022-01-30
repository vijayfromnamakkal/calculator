#!/bin/bash
echo "Unit test start"
test $(curl localhost:8765/sum?a=1\&b=2) -eq 3
echo "unit test end"
echo "UAT starts"
./gradlew acceptanceTest -Dcalculator.url=http://localhost:8765
echo "UAT ends"
