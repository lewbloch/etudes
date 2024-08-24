/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.dyspatch;

/**
 * Personal data.
 * @param signature Signature.
 * @param truthful  whether truthful.
 * @param opinion   defining opinion.
 */
public record Personal(String signature, boolean truthful, String opinion) {}
