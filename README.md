
# Simple Joda


Joda, like most datetime libraries, is needlessly complex.
Constructing cryptic datetime format strings is an error-prone task that can be alleviated by composing the datetime formats in a more human readable form.

To form a datetime format string in simplejoda, you write your format out for the reference date, `Mon Jan 2 15:04:05 MST 2006`.


For example, to format all strings in ISO 8601 GMT format, you might use the datetime string, `2006-01-02T15:04:05`.
Joda time formatters will be returned by the constructors.
Several constants will be provided.




(This libraries's API is heavily inspired by Golang's dead-simple [datetime library](http://golang.org/pkg/time/))


