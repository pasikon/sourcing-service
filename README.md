# Akka gRPC Kubernetes

This is an example of an [Akka HTTP](https://doc.akka.io/docs/akka-http/current) application communicating with an [Akka gRPC](https://developer.lightbend.com/docs/akka-grpc/current/) application inside of Kubernetes.

This example does not show Akka Cluster. If you are interested in Akka Cluster, see for the 'Cluster' examples (for [Scala](https://developer.lightbend.com/start/?group=akka&project=akka-samples-cluster-scala) or [Java](https://developer.lightbend.com/start/?group=akka&project=akka-samples-cluster-java)), 'Akka Cluster with docker-compose' (for [Scala](https://developer.lightbend.com/start/?group=akka&project=akka-sample-cluster-docker-compose-scala) or [Java](https://developer.lightbend.com/start/?group=akka&project=akka-sample-cluster-docker-compose-java)) or 'Akka Cluster on Kubernetes (for [Java](https://developer.lightbend.com/start/?group=akka&project=akka-sample-cluster-kubernetes-java))

The Akka HTTP application discovers the Akka gRPC application using [Akka Discovery](https://developer.lightbend.com/docs/akka-management/current/discovery.html).
It uses the `akka-dns` mechanism which relies on the `SRV` records created by kubernetes.

All the technologies used in this example are open source.
