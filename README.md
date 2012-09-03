Example modules for MOAT OSGi API
=================

This project introduces source code examples using [MOAT OSGi](http://inventit.edicy.co/guides/moat-iot/moat-osgi-gateway) API, a part of [MOAT IoT](http://inventit.edicy.co/guides/moat-iot).

You can build each project with [Maven2](http://maven.apache.org/). The built jar is an OSGi bundle so that you can deploy it onto an OSGi container.

Note that the bundle is depending on a bundle exporting MOAT OSGi API.

## How to build

<pre>
  mvn clean install
</pre>

You can find the generated jar files in ./target directory.

## License

All modules of this project are dual-licensed under:

* [GNU GPL v2](http://www.gnu.org/licenses/gpl-2.0.txt)
* Commercial License

Copyright © 2012 Inventit Inc.

## Change History

1.1.2 : September 3, 2012
* MOAT OSGi Example :: Simple Bundle for JDK1.4
  * Updates the version in the pom.xml.
  * Fixes an issue where <Export-Package> doesn't contain other packages than com.example.moat.
  * Adds logging on MyServiceTask and isTimeToNotify() returns true by default.
  * Changes ScheduledExecutorService instance into an OSGi service.

1.1.1 : August 30, 2012
* MOAT OSGi Example :: Simple Bundle for JDK1.4
  * Fixes an issue where a bundle activator class depends on JDK5+ classes.

1.1.0 : August 4, 2012

* MOAT OSGi Example :: Simple Bundle for JDK1.4
  * Adds a new class in order for the notification sending example to be shown in the class.

1.0.1 : August 3, 2012

* MOAT OSGi Example :: Simple Bundle for JDK1.4
  * Adds more comments on source code
  * Replaces tabs with spaces

1.0.0 : August 3, 2012

* MOAT OSGi Example :: Simple Bundle for JDK1.4 has been released.
