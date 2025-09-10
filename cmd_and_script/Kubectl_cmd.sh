$ kubectl get ns    # get all names space
$ kubectl get pods -n <ns>  # get all pods under a names space
$ kubectl get pods -n <ns> | grep clickhouse
$ kubectl get pod <pod> -n <ns>   # pod details
$ kubectl get pod <pod> -n <ns> -o yaml
$ kubectl get secrets -n <ns>
$ kubectl get services -n <ns>
$ kubectl get deployments -n <ns>
$ kubectl get cronjobs -n <ns>

$ kubectl describe pod <pod> -n <ns>

$ kubectl delete pod <pod> -n <ns>
$ kubectl delete pods --all -n <ns>

$ kubectl logs <pod> -n <ns>
$ kubectl logs <pod> -n <ns> | grep "ERROR"
$ kubectl logs -f <pod> -n <ns>   # -f for live log
$ kubectl logs --tail=20 <pod> -n <ns>  # last 20 line log

$ kubectl exec -ti <pod> -n <ns> -- sh    # to run sh cmd inside pod
$ kubectl exec -it <pod> -n <ns> -- sh    # it and ti both is same

$ kubectl scale deployment <deployment> --replicas=3
