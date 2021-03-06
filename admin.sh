#/bin/sh
if [ "$1" = "runserver" ]; then
java -cp lib/*:WebContent/WEB-INF/classes/:./conf/ -noverify -javaagent:./lib/jrebel.jar com.mob.bootstrap.Jetty
#java -cp lib/*:WebContent/WEB-INF/classes/:./conf/ com.mob.bootstrap.Jetty
elif [ "$1" = "models" ]; then
java -cp lib/*:./build com.mob.orm.DbInspector ./conf/settings.properties ./src/annotate/models annotate.models
fi
