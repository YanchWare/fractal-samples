package com.yanchware.fractal.samples.fractalfirst.infrastructure.agnostic;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PreemptionPolicy;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PriorityClass;

import java.util.ArrayList;
import java.util.List;

public class KubernetesPriorityClass {
  public static List<PriorityClass> getPriorityClasses() {
    return new ArrayList<>() {{
      add(PriorityClass.builder()
          .withName("fractal-cloud-deamonset-priority")
          .withValue(999_999_001)
          .withDescription("Specifically for DaemonSet workloads. Ensures these services, like logging and monitoring agents, run consistently across all nodes.")
          .withPreemptionPolicy(PreemptionPolicy.PREEMPT_LOWER_PRIORITY)
          .build());
      
      add(PriorityClass.builder()
          .withName("fractal-cloud-high-priority")
          .withValue(999_999_000)
          .withDescription("Reserved for critical system components that require high priority scheduling. Ideal for core networking services or essential system utilities")
          .withPreemptionPolicy(PreemptionPolicy.PREEMPT_LOWER_PRIORITY)
          .build());

      add(PriorityClass.builder()
          .withName("fractal-cloud-medium-priority")
          .withValue(900_000_000)
          .withDescription("Suited for important but not critical services. Recommended for secondary system processes and key application services that need prioritized scheduling over general workloads.")
          .withPreemptionPolicy(PreemptionPolicy.PREEMPT_LOWER_PRIORITY)
          .build());

      add(PriorityClass.builder()
          .withName("fractal-cloud-low-priority")
          .withValue(800_000_000)
          .withDescription("Designed for non-critical applications and services. Use this for workloads that can tolerate interruptions and don't require immediate resources.")
          .withPreemptionPolicy(PreemptionPolicy.PREEMPT_LOWER_PRIORITY)
          .build());
      
      add(PriorityClass.builder()
          .withName("fractal-cloud-over-provisioning")
          .withValue(1)
          .withDescription("Intended for over-provisioning pods. These low-priority workloads can be preempted to free up resources for higher-priority tasks.")
          .withPreemptionPolicy(PreemptionPolicy.PREEMPT_LOWER_PRIORITY)
          .build());
    }};
  }
}
