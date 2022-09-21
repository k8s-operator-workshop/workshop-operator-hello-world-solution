package com.workshop.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.javaoperatorsdk.operator.api.reconciler.Cleaner;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.DeleteControl;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;

public class HelloWorldReconciler implements Reconciler<HelloWorld>, Cleaner<HelloWorld> { 
  private final KubernetesClient client;

  private static final Logger log = LoggerFactory.getLogger(HelloWorldReconciler.class);

  public HelloWorldReconciler(KubernetesClient client) {
    this.client = client;
  }

  @Override
  public UpdateControl<HelloWorld> reconcile(HelloWorld resource, Context context) {
    log.info("👋 Hello, World ! From {} 🌏", resource.getSpec().getName());

    return UpdateControl.noUpdate();
  }

  @Override
  public DeleteControl cleanup(HelloWorld resource, Context<HelloWorld> context) {
    log.info("🥲  Goodbye, World ! From {}", resource.getSpec().getName());
 
    return DeleteControl.defaultDelete();  
  }
}

